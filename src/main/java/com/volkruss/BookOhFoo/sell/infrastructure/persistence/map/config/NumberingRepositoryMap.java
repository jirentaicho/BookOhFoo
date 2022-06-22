package com.volkruss.BookOhFoo.sell.infrastructure.persistence.map.config;

import com.volkruss.BookOhFoo.sell.domain.model.numbering.Numbering;
import com.volkruss.BookOhFoo.sell.domain.model.numbering.NumberingRepository;
import com.volkruss.BookOhFoo.sell.infrastructure.model.NumberingEntity;
import org.springframework.stereotype.Component;

import java.util.List;
@Component("NumberingRepositoryMap")
public class NumberingRepositoryMap implements NumberingRepository {

    private NumberingTable numberingTable = NumberingTable.getInstance();

    @Override
    public Numbering getNumber() {
        List<NumberingEntity> entities = this.numberingTable.getTableManager().getRecords();
        NumberingEntity entity =  entities.get(0);
        Numbering numbering = new Numbering(entity.getIndex());

        entity.setIndex(entity.getIndex() + 1);
        this.numberingTable.getTableManager().remove(entity);
        this.numberingTable.getTableManager().add(entity);

        return numbering;
    }
}
