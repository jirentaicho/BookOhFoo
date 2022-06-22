package com.volkruss.BookOhFoo.sell.usecase.impl;

import com.volkruss.BookOhFoo.sell.controller.RegisterForm;
import com.volkruss.BookOhFoo.book.domain.model.book.Book;
import com.volkruss.BookOhFoo.book.domain.model.book.BookRepository;
import com.volkruss.BookOhFoo.sell.domain.model.numbering.Numbering;
import com.volkruss.BookOhFoo.sell.domain.model.sell.*;
import com.volkruss.BookOhFoo.sell.domain.service.WorkTableCreateService;
import com.volkruss.BookOhFoo.sell.infrastructure.persistence.map.config.NumberingRepositoryMap;
import com.volkruss.BookOhFoo.sell.usecase.SellRegisterService;
import com.volkruss.BookOhFoo.sell.usecase.output.RegistViewOutput;
import com.volkruss.BookOhFoo.utils.message.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SellRegisterServiceImpl implements SellRegisterService {

    @Autowired
    @Qualifier("BookRepositoryMap")
    private BookRepository bookRepository;

    @Autowired
    @Qualifier("SellRepositoryMap")
    private SellRepository sellRepository;

    @Autowired
    @Qualifier("NumberingRepositoryMap")
    private NumberingRepositoryMap numberingRepositoryMap;

    @Autowired
    private WorkTableCreateService workTableCreateService;

    @Autowired
    private CalculationService calculationService;

    @Override
    public RegistViewOutput action(RegisterForm registerForm) {

        List<Detail> details = registerForm.getDetail().stream().map( i -> {
            Detail detail = new Detail(
                    i.getItemId(),
                    i.getCount(),
                    i.getDetailNo()
            );
            return detail;
        }).collect(Collectors.toList());

        // TODO lookover
        Numbering numbering = this.numberingRepositoryMap.getNumber();
        numbering.setApplication(MessageUtils.getSimpleMessage("SellApplicationName"));

        Sell sell = new Sell(
                registerForm.getName(),
                registerForm.getPhoneNumber(),
                registerForm.getReceiveWay(),
                numbering.getNumberString(),
                0,
                0
        );
        sell.registerDetail(details);

        int amount = 0;
        amount = this.calculationService.calcTotalAmt(sell.getDetails());
        sell.addAmt(amount);

        Campaign campaign = new DefaultCampaign();
        campaign = new QuantityCampaign(campaign);
        sell.applyCampaign(campaign);

        this.workTableCreateService.createWork(sell);

        RegistViewOutput registViewOutput = new RegistViewOutput();
        registViewOutput.setSellNo(sell.getSellId().getId());
        registViewOutput.setCampaign(sell.getCampaignAmt());
        registViewOutput.setAmt(sell.getAmount());

        List<RegistViewOutput.RegistViewDetail> result = new ArrayList<>();

        sell.getDetails().forEach(i -> {
            Book book = this.bookRepository.findById(i.getBookId());
            result.add(new RegistViewOutput.RegistViewDetail(
                    book.getTitle(),
                    book.getPrice().getValue(),
                    i.getCount()));
        });

        registViewOutput.setList(result);
        return registViewOutput;
    }

}
