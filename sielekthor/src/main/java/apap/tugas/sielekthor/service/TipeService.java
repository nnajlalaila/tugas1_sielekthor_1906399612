package apap.tugas.sielekthor.service;
import apap.tugas.sielekthor.model.BarangModel;
import org.springframework.stereotype.Service;
import apap.tugas.sielekthor.model.TipeModel;

import java.util.List;

public interface TipeService {
    List<TipeModel> getListTipe();
}
