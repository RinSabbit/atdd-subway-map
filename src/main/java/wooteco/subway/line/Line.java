package wooteco.subway.line;

import java.util.Objects;

public class Line {

    private Long id;
    private String color;
    private String name;

    public Line() {
    }

    public Line(Long id, String name, String color) {
        this.id = id;
        this.color = color;
        this.name = name;
    }

    public Line(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public Long getId() {
        return id;
    }

    public String getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Line line = (Line) o;
        return Objects.equals(color, line.color) && Objects.equals(name, line.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, name);
    }
}
