import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
class Entry {
    private Integer key;
    private Long value;
}
