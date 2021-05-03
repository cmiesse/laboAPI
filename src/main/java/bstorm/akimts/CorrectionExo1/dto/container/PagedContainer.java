package bstorm.akimts.CorrectionExo1.dto.container;

import lombok.Data;

import java.util.List;

@Data
public class PagedContainer<T> {

    private List<T> elements;
    private Integer nbrPage;
    private Integer nbrElement;
    private Integer page;
    private Long nbrElementTotal;

    private String nextPage;
    private String previousPage;

    public PagedContainer(List<T> elements, Integer nbrPage, Integer nbrElement, Integer page, Long nbrElementTotal) {
        this.elements = elements;
        this.nbrPage = nbrPage;
        this.nbrElement = nbrElement;
        this.nbrElementTotal = nbrElementTotal;
        this.page = page;
    }
}
