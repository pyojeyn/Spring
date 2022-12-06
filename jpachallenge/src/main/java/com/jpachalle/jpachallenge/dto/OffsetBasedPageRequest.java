package com.jpachalle.jpachallenge.dto;

import java.io.Serializable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class OffsetBasedPageRequest implements Pageable, Serializable {
    private static final long serialVersionUID = -3240185345706272829L;

    private Integer limit;
    private Long offset;
    private final Sort sort;

    public static OffsetBasedPageRequest of(Pageable pageable) {
        return new OffsetBasedPageRequest((long) (pageable.getPageNumber()), pageable.getPageSize(),
                pageable.getSort());
    }

    public static OffsetBasedPageRequest of(Pageable pageable, Sort sort) {
        return new OffsetBasedPageRequest((long) (pageable.getPageNumber()), pageable.getPageSize(),
                sort);
    }

    public static OffsetBasedPageRequest of(Long offset, Integer limit, Sort sort) {
        return new OffsetBasedPageRequest(offset, limit, sort);
    }

    /**
     * Creates a new {@link OffsetBasedPageable} with sort parameters applied.
     *
     * @param offset zero-based offset.
     * @param limit the size of the elements to be returned.
     * @param sort can be {@literal null}.
     */
    public OffsetBasedPageRequest(Long offset, Integer limit, Sort sort) {
        if (offset < 0) {
            throw new IllegalArgumentException("Offset index must not be less than zero!");
        }

        if (limit < 1) {
            throw new IllegalArgumentException("Limit must not be less than one!");
        }

        this.limit = limit;
        this.offset = offset;
        this.sort = sort;
    }

    /**
     * Creates a new {@link OffsetBasedPageable} with sort parameters applied.
     *
     * @param offset zero-based offset.
     * @param limit the size of the elements to be returned.
     * @param properties the properties to sort by, must not be {@literal null} or empty.
     */
    public OffsetBasedPageRequest(Long offset, Integer limit, String properties) {
        this(offset, limit);
    }

    /**
     * Creates a new {@link OffsetBasedPageable} with sort parameters applied.
     *
     * @param offset zero-based offset.
     * @param limit the size of the elements to be returned.
     * @param properties the properties to sort by, must not be {@literal null} or empty.
     */
    public OffsetBasedPageRequest(Long offset, Integer limit, String... properties) {
        this(offset, limit);
    }

    /**
     * Creates a new {@link OffsetBasedPageable} with sort parameters applied.
     *
     * @param offset zero-based offset.
     * @param limit the size of the elements to be returned.
     * @param direction the direction of the {@link Sort} to be specified, can be {@literal null}.
     * @param properties the properties to sort by, must not be {@literal null} or empty.
     */
    public OffsetBasedPageRequest(Long offset, Integer limit, Sort.Direction direction,
                                  String... properties) {
        this(offset, limit, Sort.by(direction, properties));
    }

    /**
     * Creates a new {@link OffsetBasedPageable} with sort parameters applied.
     *
     * @param offset zero-based offset.
     * @param limit the size of the elements to be returned.
     */
    public OffsetBasedPageRequest(Long offset, Integer limit) {
        this(offset, limit, Sort.unsorted());
    }

    public OffsetBasedPageRequest() {
        this(0L, 20, Sort.unsorted());
    }

    @Override
    public int getPageNumber() {
        return Math.toIntExact(offset / limit);
    }

    @Override
    public int getPageSize() {
        return limit;
    }

    @Override
    public long getOffset() {
        return offset;
    }

    @Override
    public Sort getSort() {
        return sort;
    }

    @Override
    public Pageable next() {
        return new OffsetBasedPageRequest(getOffset() + getPageSize(), getPageSize(), getSort());
    }

    public OffsetBasedPageRequest previous() {
        return hasPrevious()
                ? new OffsetBasedPageRequest(getOffset() - getPageSize(), getPageSize(), getSort())
                : this;
    }

    @Override
    public Pageable previousOrFirst() {
        return hasPrevious() ? previous() : first();
    }

    @Override
    public Pageable first() {
        return new OffsetBasedPageRequest(0L, getPageSize(), getSort());
    }

    @Override
    public boolean hasPrevious() {
        return offset > limit;
    }

    @Override
    public Pageable withPage(int pageNumber) {
        return null;
    }
}
