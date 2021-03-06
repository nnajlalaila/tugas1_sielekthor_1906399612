package apap.tugas.sielekthor.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
@Entity
@Table(name = "pembelianbarang")
public class PembelianBarangModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private Date tanggalGaransi;

    @NotNull
    @Column(nullable = false)
    private Integer quantity;


    @ManyToOne()
    @JoinColumn(name = "IdPembelian")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private PembelianModel pembelian;

    @ManyToOne()
    @JoinColumn(name = "IdBarang")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private BarangModel barang;

}
