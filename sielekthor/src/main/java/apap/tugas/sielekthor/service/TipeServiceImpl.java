package apap.tugas.sielekthor.service;
import apap.tugas.sielekthor.model.BarangModel;
import apap.tugas.sielekthor.repository.BarangDB;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import apap.tugas.sielekthor.repository.TipeDB;
import apap.tugas.sielekthor.model.TipeModel;
import java.util.List;

@Service
@Transactional
public class TipeServiceImpl implements  TipeService{
    @Autowired
    TipeDB tipeDB;

    @Override
    public List<TipeModel> getListTipe(){
        return tipeDB.findAll();
    }
}
