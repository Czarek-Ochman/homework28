package pl.javastart.sellegro.services;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import pl.javastart.sellegro.model.AuctionFilters;
import pl.javastart.sellegro.model.Auctions;
import pl.javastart.sellegro.repository.AuctionRepository;

import java.util.List;
import java.util.Random;

@Service
public class AuctionsServices {

    private AuctionRepository auctionRepository;

    public AuctionsServices(AuctionRepository auctionRepository) {
        this.auctionRepository = auctionRepository;
    }

    public void getAuctions() {
        final String[] ADJECTIVES = {"Niesamowity", "Jedyny taki", "IGŁA", "HIT", "Jak nowy",
                "Perełka", "OKAZJA", "Wyjątkowy"};

        List<Auctions> allAuctions = auctionRepository.findAll();
        Random random = new Random();

        for (int i = 0; i < allAuctions.size(); i++) {
            String randomAdjective = ADJECTIVES[random.nextInt(ADJECTIVES.length)];
            String title = randomAdjective + " " + allAuctions.get(i).getCarMake() + " " + allAuctions.get(i).getCarModel();
            auctionRepository.update(title, (long) i + 1);
        }
    }

    public List<Auctions> findAllSorted(String sort) {
        if (sort.equals("title")) {
            return auctionRepository.findAndSortTitle();
        } else if (sort.equals("price")) {
            return auctionRepository.findAndSortPrice();
        } else if (sort.equals("color")) {
            return auctionRepository.findAndSortColor();
        } else if (sort.equals("endDate")) {
            return auctionRepository.findAndSortEndDate();
        }
        return auctionRepository.findAll();
    }

    public List<Auctions> getAuctionsWithFilters(@RequestParam(required = false) String sort, AuctionFilters auctionFilters) {
        List<Auctions> auctions;
        if (sort != null) {
            auctions = findAllSorted(sort);
        } else if (auctionFilters.getTitle() != null && auctionFilters.getTitle().length() > 0 && auctionFilters.getCarMaker() != null && auctionFilters.getCarMaker().length() > 0 && auctionFilters.getCarModel() != null && auctionFilters.getCarModel().length() > 0 && auctionFilters.getColor() != null && auctionFilters.getColor().length() > 0) {
            auctions = auctionRepository.getAuctionsWithTitleAndCarMakeAndAndCarModelAndColor(auctionFilters.getTitle(), auctionFilters.getCarMaker(), auctionFilters.getCarModel(), auctionFilters.getColor());
        } else if (auctionFilters.getTitle() != null && auctionFilters.getTitle().length() > 0 && auctionFilters.getCarMaker() != null && auctionFilters.getCarMaker().length() > 0 && auctionFilters.getCarModel() != null && auctionFilters.getCarModel().length() > 0) {
            auctions = auctionRepository.getAuctionsWithTitleAndCarMakeAndAndCarModel(auctionFilters.getTitle(), auctionFilters.getCarMaker(), auctionFilters.getCarModel());
        } else if (auctionFilters.getTitle() != null && auctionFilters.getTitle().length() > 0 && auctionFilters.getCarMaker() != null && auctionFilters.getCarMaker().length() > 0 && auctionFilters.getColor() != null && auctionFilters.getColor().length() > 0) {
            auctions = auctionRepository.getAuctionsWithTitleAndCarMakeAndAndColor(auctionFilters.getTitle(), auctionFilters.getCarMaker(), auctionFilters.getColor());
        } else if (auctionFilters.getTitle() != null && auctionFilters.getTitle().length() > 0 && auctionFilters.getColor() != null && auctionFilters.getColor().length() > 0 && auctionFilters.getCarModel() != null && auctionFilters.getCarModel().length() > 0) {
            auctions = auctionRepository.getAuctionsWithTitleAndCarModelAndColor(auctionFilters.getTitle(), auctionFilters.getCarModel(), auctionFilters.getColor());
        } else if (auctionFilters.getColor() != null && auctionFilters.getColor().length() > 0 && auctionFilters.getCarMaker() != null && auctionFilters.getCarMaker().length() > 0 && auctionFilters.getCarModel() != null && auctionFilters.getCarModel().length() > 0) {
            auctions = auctionRepository.getAuctionsWithCarMakeAndAndCarModelAndColor(auctionFilters.getCarMaker(), auctionFilters.getCarModel(), auctionFilters.getColor());
        } else if (auctionFilters.getTitle() != null && auctionFilters.getTitle().length() > 0 && auctionFilters.getCarMaker() != null && auctionFilters.getCarMaker().length() > 0) {
            auctions = auctionRepository.getAuctionsWithTitleAndCarMake(auctionFilters.getTitle(), auctionFilters.getCarMaker());
        } else if (auctionFilters.getTitle() != null && auctionFilters.getTitle().length() > 0 && auctionFilters.getCarModel() != null && auctionFilters.getCarModel().length() > 0) {
            auctions = auctionRepository.getAuctionsWithTitleAndCarModel(auctionFilters.getTitle(), auctionFilters.getCarModel());
        } else if (auctionFilters.getTitle() != null && auctionFilters.getTitle().length() > 0 && auctionFilters.getColor() != null && auctionFilters.getColor().length() > 0) {
            auctions = auctionRepository.getAuctionsWithTitleAndColor(auctionFilters.getTitle(), auctionFilters.getColor());
        } else if (auctionFilters.getCarMaker() != null && auctionFilters.getCarMaker().length() > 0 && auctionFilters.getCarModel() != null && auctionFilters.getCarModel().length() > 0) {
            auctions = auctionRepository.getAuctionsWithCarMakeAndCarModel(auctionFilters.getCarMaker(), auctionFilters.getCarModel());
        } else if (auctionFilters.getCarMaker() != null && auctionFilters.getCarMaker().length() > 0 && auctionFilters.getColor() != null && auctionFilters.getColor().length() > 0) {
            auctions = auctionRepository.getAuctionsWithCarMakeAndColor(auctionFilters.getCarMaker(), auctionFilters.getColor());
        } else if (auctionFilters.getCarModel() != null && auctionFilters.getCarModel().length() > 0 && auctionFilters.getColor() != null && auctionFilters.getColor().length() > 0) {
            auctions = auctionRepository.getAuctionsWithCarModelAndColor(auctionFilters.getCarModel(), auctionFilters.getColor());
        } else if (auctionFilters.getTitle() != null && auctionFilters.getTitle().length() > 0) {
            auctions = auctionRepository.getAuctionsWithTitle(auctionFilters.getTitle());
        } else if (auctionFilters.getCarMaker() != null && auctionFilters.getCarMaker().length() > 0) {
            auctions = auctionRepository.getAuctionsWithCarMake(auctionFilters.getCarMaker());
        } else if (auctionFilters.getCarModel() != null && auctionFilters.getCarModel().length() > 0) {
            auctions = auctionRepository.getAuctionsWithCarModel(auctionFilters.getCarModel());
        } else if (auctionFilters.getColor() != null && auctionFilters.getColor().length() > 0) {
            auctions = auctionRepository.getAuctionsWithColor(auctionFilters.getColor());
        } else {
            auctions = auctionRepository.findAll();
        }
        return auctions;
    }
}