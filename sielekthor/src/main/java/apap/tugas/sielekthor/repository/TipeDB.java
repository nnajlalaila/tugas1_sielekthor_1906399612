package apap.tugas.sielekthor.repository;
import  apap.tugas.sielekthor.model.TipeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TipeDB extends JpaRepository<TipeModel,Long> {
    List<TipeModel> findAll();
}