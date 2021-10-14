package apap.tugas.sielekthor.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import apap.tugas.sielekthor.repository.BarangDB;
import apap.tugas.sielekthor.model.BarangModel;
import java.util.List;

@Service
@Transactional
public class BarangServiceImpl implements BarangService {
    @Autowired
    BarangDB barangDB;

    @Override
    public void addBarang(BarangModel barang){
        barangDB.save(barang);
    }
    @Override
    public  void updateBarang(BarangModel barang){
        barangDB.save(barang);
    }

    @Override
    public List<BarangModel> getListBarang(){
        return barangDB.findAll();
    }

    @Override
    public BarangModel getBarangById(Long id){
        return barangDB.getBarangModelById(id);
    }
}
