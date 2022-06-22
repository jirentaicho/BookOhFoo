package com.volkruss.BookOhFoo.sell.infrastructure.persistence.map.sell;

import com.volkruss.BookOhFoo.sell.controller.ItemDetail;
import com.volkruss.BookOhFoo.sell.domain.model.sell.Detail;
import com.volkruss.BookOhFoo.sell.domain.model.sell.Sell;
import com.volkruss.BookOhFoo.sell.domain.model.sell.SellId;
import com.volkruss.BookOhFoo.sell.domain.model.sell.SellRepository;
import com.volkruss.BookOhFoo.sell.infrastructure.model.sell.SellDetailEntity;
import com.volkruss.BookOhFoo.sell.infrastructure.model.sell.SellDetailWorkEntity;
import com.volkruss.BookOhFoo.sell.infrastructure.model.sell.SellHeaderEntity;
import com.volkruss.BookOhFoo.sell.infrastructure.model.sell.SellHeaderWorkEntity;
import com.volkruss.BookOhFoo.book.infrastructure.persistence.map.book.BookTable;
import com.volkruss.BookOhFoo.utils.date.DateUtil;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// TODO ワークとクラスを分ける
@Component("SellRepositoryMap")
public class SellRepositoryMap implements SellRepository {

    private BookTable bookTable = BookTable.getInstance();

    private SellHeaderTable sellHeaderTable = SellHeaderTable.getInstance();

    private SellDetailTable sellDetailTable = SellDetailTable.getInstance();

    private SellHeaderWorkTable headerWorkTable = SellHeaderWorkTable.getInstance();

    private SellDetailWorkTable detailWorkTable = SellDetailWorkTable.getInstance();

    @Override
    public List<Detail> getDetails(List<ItemDetail> details) {
        return null;
    }

    @Override
    public void storeHead(Sell sell) {
        String createdAt = DateUtil.getCurrentStr();
        SellHeaderEntity entity = new SellHeaderEntity(
                0,
                sell.getSellId().getId(),
                sell.getSellerName(),
                sell.getPhoneNumber(),
                sell.getAmount(),
                sell.getCampaignAmt(),
                sell.getWay(),
                createdAt
        );
        sellHeaderTable.getTableManager().add(entity);
    }

    @Override
    public void storeDetail(SellId sellId, List<Detail> details) {
        // ワークテーブルに保存します
        List<SellDetailEntity> detailEntities = details.stream().map(i -> {
            SellDetailEntity detailEntity = new SellDetailEntity();
            detailEntity.setBookId(i.getBookIdStr());
            detailEntity.setSellId(sellId.getId());
            detailEntity.setCount(i.getCount());
            detailEntity.setDetailNo(0);
            return detailEntity;
        }).collect(Collectors.toList());
        // 登録する
        sellDetailTable.getTableManager().addAll(detailEntities);
    }

    @Override
    public void storeWorkHead(Sell sell) {
        // 日付を取得する
        String createdAt = DateUtil.getCurrentStr();
        SellHeaderWorkEntity entity = new SellHeaderWorkEntity(
                0,
                sell.getSellId().getId(),
                sell.getSellerName(),
                sell.getPhoneNumber(),
                sell.getAmount(),
                sell.getCampaignAmt(),
                sell.getWay(),
                createdAt
        );
        headerWorkTable.getTableManager().add(entity);
    }

    @Override
    public void storeWorkDetail(SellId sellId,List<Detail> details) {
        // ワークテーブルに保存します
        List<SellDetailWorkEntity> wks = details.stream().map(i -> {
            SellDetailWorkEntity wk = new SellDetailWorkEntity();
            wk.setBookId(i.getBookIdStr());
            wk.setSellId(sellId.getId());
            wk.setCount(i.getCount());
            wk.setDetailNo(0);
            return wk;
        }).collect(Collectors.toList());
        // 登録する
        detailWorkTable.getTableManager().addAll(wks);
    }

    @Override
    public Sell findSellBySellId(SellId sellId) {
        // ヘッダの取得
        List<SellHeaderWorkEntity> headers = headerWorkTable.getTableManager().getRecords();
        SellHeaderWorkEntity header = headers
                .stream()
                .filter(i -> i.getSellId().equals(sellId.getId()))
                .findFirst().get();
        Sell sell = new Sell(
                header.getName(),
                header.getPhone(),
                header.getWay(),
                header.getSellId(),
                header.getTotalAmt(),
                header.getDiscount()
        );

        // フッタの取得
        List<SellDetailWorkEntity> allDetails = detailWorkTable.getTableManager().getRecords();
        List<SellDetailWorkEntity> entities = allDetails
                .stream()
                .filter(i -> i.getSellId().equals(sellId.getId()))
                .collect(Collectors.toList());
        List<Detail> details = entities
                .stream()
                .map(i -> {
                    Detail detail = new Detail(
                            i.getBookId(),
                            i.getCount(),
                            0
                    );
                    return detail;
                }).collect(Collectors.toList());
        sell.registerDetail(details);

        return sell;
    }

    @Override
    public List<Sell> findAll() {

        // TODO refactor
        List<SellHeaderEntity> headers = sellHeaderTable.getTableManager().getRecords();

        List<Sell> sells = new ArrayList<>();

        for(SellHeaderEntity header: headers){
            List<SellDetailEntity> detailEntities = this.sellDetailTable.getTableManager().getRecords();
            List<SellDetailEntity> idDetailEntities = detailEntities.stream().filter(i-> i.getSellId().equals(header.getSellId())).collect(Collectors.toList());

            List<Detail> details = idDetailEntities.stream().map(i -> {
                Detail detail = new Detail(
                        i.getBookId(),
                        i.getCount(),
                        0
                );
                return detail;
            }).collect(Collectors.toList());

            Sell sell = new Sell(
                    header.getName(),
                    header.getPhone(),
                    header.getWay(),
                    header.getSellId(),
                    header.getTotalAmt(),
                    header.getDiscount()
            );
            sell.registerDetail(details);

            sells.add(sell);
        }
        return sells;
    }

}
