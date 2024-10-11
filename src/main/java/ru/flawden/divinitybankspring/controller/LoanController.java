package ru.flawden.divinitybankspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.flawden.divinitybankspring.dto.LoanDTO;
import ru.flawden.divinitybankspring.entity.Loan;
import ru.flawden.divinitybankspring.entity.LoanOffer;
import ru.flawden.divinitybankspring.entity.Person;
import ru.flawden.divinitybankspring.service.LoanOfferService;
import ru.flawden.divinitybankspring.service.LoanService;
import ru.flawden.divinitybankspring.service.PeopleService;

import java.security.Principal;
import java.util.List;

/**
 * Controller for handling loan-related operations, such as viewing loans, creating new loans, and managing loan offers.
 *
 * @author Flawden
 * @version 1.0
 */
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

    /**
     * Displays the loan list page for the logged-in user.
     *
     * @param model Holds the list of loans to be displayed.
     * @param principal The logged-in user's principal object.
     * @return The loan list view.
     */
    @GetMapping
    public String getLoanListPage(Model model, Principal principal) {
        Person person = peopleService.findByEmail(principal.getName());
        List<Loan> loans = loanService.findAllByPerson(person);
        model.addAttribute("loans", loans);
        return "profile/loan";
    }

    /**
     * Displays the loan creation page, allowing the user to select a loan offer.
     *
     * @param loanDTO Object to bind loan creation form data.
     * @param model Holds available loan offers for display.
     * @return The loan creation view.
     */
    @GetMapping("/create-loan")
    public String createLoanPage(@ModelAttribute LoanDTO loanDTO, Model model) {
        List<LoanOffer> options = loanOfferService.findAll();
        model.addAttribute("options", options);
        return "profile/loan/create-loan";
    }

    /**
     * Handles the loan creation request and saves the new loan for the logged-in user.
     *
     * @param loanDTO Object holding loan creation form data.
     * @param principal The logged-in user's principal object.
     * @return Redirects to the account page after creating the loan.
     */
    @PostMapping
    public String createLoan(@ModelAttribute LoanDTO loanDTO, Principal principal) {
        loanService.save(loanDTO, principal.getName());
        return "redirect:/account";
    }

}
