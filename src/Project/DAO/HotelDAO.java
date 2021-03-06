package Project.DAO;

import Project.exception.BadRequestException;
import Project.exception.InternalServerException;
import Project.model.Hotel;

import java.util.LinkedList;

public class HotelDAO extends DAOTools<Hotel> {

    public HotelDAO() {
        super("E:/Project/HotelDb.txt");
    }

    public LinkedList<Hotel> findHotelByName(String name) throws InternalServerException, BadRequestException {
        LinkedList<Hotel> resultHotels = new LinkedList<>();

        for (Hotel hotel : getObjectsFromDAO()) {
            if (hotel.getName().equals(name)) resultHotels.add(hotel);
        }

        if (resultHotels.size() == 0) {
            throw new BadRequestException("checkSize failed: there is no hotels with this parameters");
        }
        return resultHotels;
    }

    public LinkedList<Hotel> findHotelByCity(String city) throws InternalServerException, BadRequestException {
        LinkedList<Hotel> resultHotels = new LinkedList<>();

        for (Hotel hotel : getObjectsFromDAO()) {
            if (hotel.getCity().equals(city)) resultHotels.add(hotel);
        }

        if (resultHotels.size() == 0) {
            throw new BadRequestException("checkSize failed: there is no hotels with this parameters");
        }
        return resultHotels;
    }

    @Override
    public Hotel map(String line) {
        String[] fields = line.split(", ");

        return new Hotel(Long.parseLong(fields[0]), fields[1], fields[2], fields[3], fields[4]);
    }

    public void doesTheHotelExist(Hotel hotel) throws InternalServerException, BadRequestException {
        for (Hotel hotel1 : getObjectsFromDAO()) {
            if (hotel1.equals(hotel))
                throw new BadRequestException("isExist failed: the hotel is already exist: " + hotel1.getId());
        }
    }
}