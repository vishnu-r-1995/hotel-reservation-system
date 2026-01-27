@Service
public class HotelService {

    private final HotelRepository repository;

    public HotelService(HotelRepository repository) {
        this.repository = repository;
    }

    public Hotel create(Hotel hotel) {
        return repository.save(hotel);
    }

    public Hotel get(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Hotel not found"));
    }

    public List<Hotel> getAll() {
        return repository.findAll();
    }
}