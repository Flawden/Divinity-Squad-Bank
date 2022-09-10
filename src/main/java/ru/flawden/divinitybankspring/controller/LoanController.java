package ru.flawden.divinitybankspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.flawden.divinitybankspring.dto.LoanDTO;
import ru.flawden.divinitybankspring.entity.Card;
import ru.flawden.divinitybankspring.entity.Loan;
import ru.flawden.divinitybankspring.entity.LoanOffer;
import ru.flawden.divinitybankspring.entity.Person;
import ru.flawden.divinitybankspring.service.LoanOfferService;
import ru.flawden.divinitybankspring.service.LoanService;
import ru.flawden.divinitybankspring.service.PeopleService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/loans")
public class LoanController {

    private final LoanService loanService;
    private final LoanOfferService loanOfferService;
    private final PeopleService peopleService;

    public LoanController(LoanService loanService, LoanOfferService loanOfferService, PeopleService peopleService) {
        this.loanService = loanService;
        this.loanOfferService = loanOfferService;
        this.peopleService = peopleService;
    }

    @GetMapping
    public String getLoanListPage(Model model, Principal principal) {
        Person person = peopleService.findByEmail(principal.getName());
        List<Loan> loans = loanService.findAllByPerson(person);
        model.addAttribute("loans", loans);
        return "profile/loan";
    }

    @GetMapping("/create-loan")
    public String createLoanPage(@ModelAttribute LoanDTO loanDTO, Model model) {
        List<LoanOffer> offers = loanOfferService.findAll();
        List<String> options = new ArrayList<>();
        offers.forEach(offer -> options.add(offer.getCreditName()));
        model.addAttribute("options", options);
        return "profile/loan/create-loan";
    }

    @PostMapping
    public String createLoan(@ModelAttribute LoanDTO loanDTO, Principal principal) {
        loanService.save(loanDTO, principal.getName());
        return "redirect:/account";
    }

}
