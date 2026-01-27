@Service
public class RoomService {

    private final RoomRepository repository;
    private final HotelRepository hotelRepository;

    public RoomService(RoomRepository repository, HotelRepository hotelRepository) {
        this.repository = repository;
        this.hotelRepository = hotelRepository;
    }

    public Room createRoom(Long hotelId, Room room) {
        hotelRepository.findById(hotelId)
            .orElseThrow(() -> new IllegalArgumentException("Hotel not found"));

        room.setHotelId(hotelId);
        room.setActive(true);
        return repository.save(room);
    }

    public List<Room> getActiveRooms(Long hotelId) {
        return repository.findByHotelIdAndActiveTrue(hotelId);
    }
}