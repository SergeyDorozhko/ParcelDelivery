package org.darozhka.parceldelivery.commons.dto;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * @author S.Darozhka
 */
public class PageDto<T> {

    @JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "@class")
    private List<T> content;
    private int pageNumber;
    private int pageSize;
    private long totalElements;

    public PageDto() {
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        PageDto<?> pageDto = (PageDto<?>) obj;
        return
                pageNumber == pageDto.pageNumber &&
                        pageSize == pageDto.pageSize &&
                        totalElements == pageDto.totalElements &&
                        Objects.equals(content, pageDto.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content, pageNumber, pageSize, totalElements);
    }

    @Override
    public String toString() {
        return "PageDto{" +
                "content=" + content +
                ", pageNumber=" + pageNumber +
                ", pageSize=" + pageSize +
                ", totalElements=" + totalElements +
                '}';
    }

    public static <T> PageDto<T> from(Page<T> page) {
        return from(page, null);
    }

    @SuppressWarnings("unchecked")
    public static <T, D> PageDto<D> from(Page<T> page, Function<T, D> contentMapper) {
        if (page == null) {
            return null;
        }

        PageDto<D> dto = new PageDto<>();
        if (contentMapper == null) {
            dto.setContent((List<D>) page.getContent());
        } else {
            dto.setContent(page.getContent().stream()
                    .map(contentMapper)
                    .collect(Collectors.toList()));
        }
        dto.setPageNumber(page.getNumber());
        dto.setPageSize(page.getSize());
        dto.setTotalElements(page.getTotalElements());
        return dto;
    }

}