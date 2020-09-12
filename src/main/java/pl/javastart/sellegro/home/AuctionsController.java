package pl.javastart.sellegro.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.javastart.sellegro.model.AuctionFilters;
import pl.javastart.sellegro.model.Auctions;
import pl.javastart.sellegro.repository.AuctionRepository;
import pl.javastart.sellegro.services.AuctionsServices;

import java.util.List;

@Controller
public class AuctionsController {

    private AuctionsServices auctionsServices;
    private AuctionRepository auctionRepository;

    @Autowired
    public AuctionsController(AuctionRepository auctionRepository, AuctionsServices auctionsServices) {
        this.auctionRepository = auctionRepository;
        this.auctionsServices = auctionsServices;
        auctionsServices.getAuctions();
    }

    @GetMapping("/")
    public String home(Model model) {
        List<Auctions> auctions = auctionRepository.findTop4ByOrderByPriceDesc();
        model.addAttribute("cars", auctions);
        return "home";
    }

    @GetMapping("/auctions")
    public String auctions(Model model,
                           @RequestParam(required = false) String sort,
                           AuctionFilters auctionFilters) {

        List<Auctions> auctions;

        auctions = auctionsServices.getAuctionsWithFilters(sort, auctionFilters);

        model.addAttribute("cars", auctions);
        model.addAttribute("filters", auctionFilters);
        return "auctions";
    }
}