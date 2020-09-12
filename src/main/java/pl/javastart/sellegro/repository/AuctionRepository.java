package pl.javastart.sellegro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.javastart.sellegro.model.Auctions;

import javax.transaction.Transactional;
import java.util.List;

public interface AuctionRepository extends JpaRepository<Auctions, Long> {

    public List<Auctions> findTop4ByOrderByPriceDesc();

    @Modifying
    @Transactional
    @Query("UPDATE Auctions SET title = :title WHERE id = :id")
    public void update(@Param("title") String title, @Param("id") Long id);

    @Query("SELECT a FROM Auctions a " +
            "WHERE a.title LIKE CONCAT('%',:title, '%') " +
            "AND a.carMake LIKE :carMake " +
            "AND a.carModel LIKE :carModel " +
            "AND a.color LIKE:color ")
    List<Auctions> getAuctionsWithTitleAndCarMakeAndAndCarModelAndColor(String title, String carMake, String carModel, String color);

    @Query("SELECT a FROM Auctions a " +
            "WHERE a.title LIKE CONCAT('%',:title, '%') " +
            "AND a.carMake LIKE :carMake " +
            "AND a.carModel LIKE :carModel")
    List<Auctions> getAuctionsWithTitleAndCarMakeAndAndCarModel(String title, String carMake, String carModel);

    @Query("SELECT a FROM Auctions a " +
            "WHERE a.title LIKE CONCAT('%',:title, '%') " +
            "AND a.carMake LIKE :carMake " +
            "AND a.color LIKE :color")
    List<Auctions> getAuctionsWithTitleAndCarMakeAndAndColor(String title, String carMake, String color);

    @Query("SELECT a FROM Auctions a " +
            "WHERE a.title LIKE CONCAT('%',:title, '%') " +
            "AND a.carModel LIKE :carModel " +
            "AND a.color LIKE :color")
    List<Auctions> getAuctionsWithTitleAndCarModelAndColor(String title, String carModel, String color);

    @Query("SELECT a FROM Auctions a " +
            "WHERE  a.carMake LIKE :carMake " +
            "AND a.carModel LIKE :carModel " +
            "AND a.color LIKE :color")
    List<Auctions> getAuctionsWithCarMakeAndAndCarModelAndColor(String carMake, String carModel, String color);

    @Query("SELECT a FROM Auctions a " +
            "WHERE a.title LIKE CONCAT('%',:title, '%') " +
            "AND a.carMake LIKE :carMake")
    List<Auctions> getAuctionsWithTitleAndCarMake(String title, String carMake);

    @Query("SELECT a FROM Auctions a " +
            "WHERE a.title LIKE CONCAT('%',:title, '%') " +
            "AND a.carModel LIKE :carModel")
    List<Auctions> getAuctionsWithTitleAndCarModel(String title, String carModel);

    @Query("SELECT a FROM Auctions a " +
            "WHERE a.title LIKE CONCAT('%',:title, '%') " +
            "AND a.color LIKE :color")
    List<Auctions> getAuctionsWithTitleAndColor(String title, String color);

    @Query("SELECT a FROM Auctions a " +
            "WHERE a.carMake LIKE :carMake " +
            "AND a.carModel LIKE :carModel")
    List<Auctions> getAuctionsWithCarMakeAndCarModel(String carMake, String carModel);

    @Query("SELECT a FROM Auctions a " +
            "WHERE a.carMake LIKE :carMake " +
            "AND a.color LIKE :color")
    List<Auctions> getAuctionsWithCarMakeAndColor(String carMake, String color);

    @Query("SELECT a FROM Auctions a " +
            "WHERE a.carModel LIKE :carModel " +
            "AND a.color LIKE :color")
    List<Auctions> getAuctionsWithCarModelAndColor(String carModel, String color);

    @Query("SELECT a FROM Auctions a " +
            "WHERE a.title LIKE CONCAT('%',:title, '%') ")
    List<Auctions> getAuctionsWithTitle(String title);

    @Query("SELECT a FROM Auctions a " +
            "WHERE a.carMake LIKE :carMake ")
    List<Auctions> getAuctionsWithCarMake(String carMake);

    @Query("SELECT a FROM Auctions a " +
            "WHERE a.carModel LIKE :carModel ")
    List<Auctions> getAuctionsWithCarModel(String carModel);

    @Query("SELECT a FROM Auctions a " +
            "WHERE a.color LIKE :color ")
    List<Auctions> getAuctionsWithColor(String color);

    @Query("SELECT a FROM Auctions a ORDER BY a.endDate ASC")
    List<Auctions> findAndSortEndDate();

    @Query("SELECT a FROM Auctions a ORDER BY a.price ASC")
    List<Auctions> findAndSortPrice();

    @Query("SELECT a FROM Auctions a ORDER BY a.title ASC")
    List<Auctions> findAndSortTitle();

    @Query("SELECT a FROM Auctions a ORDER BY a.color ASC")
    List<Auctions> findAndSortColor();
}