
package com.elcom.management_library_data.mysql.model;

import com.elcom.management_library_data.mysql.dto.CategoryWithBookCount;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "category")

// Định nghĩa 1 native query trả kết quả về một dto class 
@NamedNativeQuery(
    name = "CategoryWithBookCount",
    query = "select c.id, count(b.id) as total from book as b left join category as c on b.category_id = c.id group by c.id",
    resultSetMapping = "CategoryWithBookCountMapping"
)
@SqlResultSetMapping(
    name = "CategoryWithBookCountMapping",
    classes = {
        @ConstructorResult(
            columns = {
                @ColumnResult(name = "c.id", type = Long.class),
                @ColumnResult(name = "total", type = int.class)
            },
            targetClass = CategoryWithBookCount.class
        )
    }
)
public class Category extends Base implements Serializable{
    @Column
    private String name;
    
    @OneToMany(mappedBy = "category")
    private List<Book> books = new ArrayList<>();

    @Override
    public String toString() {
        return "Category{" + "name=" + name + ", books=";
    }

    
    
}
