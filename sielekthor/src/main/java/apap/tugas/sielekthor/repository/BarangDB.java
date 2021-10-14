package apap.tugas.sielekthor.repository;
import  apap.tugas.sielekthor.model.BarangModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BarangDB extends JpaRepository<BarangModel,Long> {
    BarangModel getBarangModelById(Long id);
    List<BarangModel> findAll();
}

