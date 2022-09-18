package bsep.mojakuca.dto;

public class DoubleIdnetificationDTO {

    private Long first;
    private Long second;

    public DoubleIdnetificationDTO(Long first, Long second) {
        this.first = first;
        this.second = second;
    }

    public DoubleIdnetificationDTO() {
    }

    public Long getFirst() {
        return first;
    }

    public void setFirst(Long first) {
        this.first = first;
    }

    public Long getSecond() {
        return second;
    }

    public void setSecond(Long second) {
        this.second = second;
    }
}
