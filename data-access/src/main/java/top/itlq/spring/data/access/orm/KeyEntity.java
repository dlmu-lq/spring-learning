package top.itlq.spring.data.access.orm;

public class KeyEntity {
    private String id;
    private Long number;

    public KeyEntity(String id, Long number) {
        this.id = id;
        this.number = number;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }
}
