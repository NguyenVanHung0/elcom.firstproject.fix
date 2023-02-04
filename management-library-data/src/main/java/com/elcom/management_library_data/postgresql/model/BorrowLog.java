package com.elcom.management_library_data.postgresql.model;

import com.elcom.management_library_data.postgresql.dto.BorrowLogNameWithTime;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "borrow_log")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@NamedNativeQuery(
        name = "maxBorrowLogBook",
        query = "select count(*) as count, book_name as name \n"
        + "from borrow_log \n"
        + "where borrow_date between ?1 and ?2 \n"
        + "group by book_name \n"
        + "having count(*) = (select max(count_number) \n"
        + "				   from (select count(*) count_number, book_name \n"
        + "						from borrow_log \n"
        + "						where borrow_date between ?1 and ?2 \n"
        + "						group by book_name) as bang1\n"
        + "				  )",
        resultSetMapping = "maxBorrowLogBookMapping"
)
@SqlResultSetMapping(
        name = "maxBorrowLogBookMapping",
        classes = {
            @ConstructorResult(
                    columns = {
                        @ColumnResult(name = "count", type = Long.class),
                        @ColumnResult(name = "name", type = String.class)
                    },
                    targetClass = BorrowLogNameWithTime.class
            )
        }
)
public class BorrowLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "author_name")
    private String authorName;

    @Column(name = "book_name")
    private String bookName;

    @Column(name = "category_book")
    private String categoryBook;

    @Column(name = "borrow_date")
    private Date borrowDate;
}
