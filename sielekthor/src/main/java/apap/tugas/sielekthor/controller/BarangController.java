package apap.tugas.sielekthor.controller;
import apap.tugas.sielekthor.model.BarangModel;
import apap.tugas.sielekthor.model.TipeModel;
import apap.tugas.sielekthor.service.BarangService;
import apap.tugas.sielekthor.service.TipeService;
import apap.tugas.sielekthor.service.BarangServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;
import javax.servlet.http.HttpServletRequest;
import java.util. ArrayList;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
public class BarangController {
    @Qualifier("tipeServiceImpl")
    @Autowired
    private TipeService tipeService;
    @Qualifier("barangServiceImpl")
    @Autowired
    private BarangService barangService;

    @GetMapping("/barang")
    public String viewAllbarang(Model model){
        List<BarangModel> barang = barangService.getListBarang();
        model.addAttribute("listBarang", barang);
        return "viewall-barang";
    }

    @GetMapping("/barang/tambah")
    public String addBarangForm(Model model){
        model.addAttribute("barang", new BarangModel());
        model.addAttribute("listTipe", tipeService.getListTipe());
        return "form-add-barang";
    }

    @PostMapping("/barang/tambah")
    public String addBarangSubmit(
            @ModelAttribute BarangModel barang,
            Model model
    ){
        barangService.addBarang(barang);
        model.addAttribute("kodeBarang", barang.getKodeBarang());
        return "add-barang";
    }


    @GetMapping("/barang/{idBarang}")
    public String viewDetailBarang(
            @PathVariable Long idBarang,
            Model model
    ) {
        BarangModel barang = barangService.getBarangById(idBarang);
        if (barang != null){
            model.addAttribute( "barang", barang);
            return "view-detail-barang";
        } else {
            model.addAttribute( "idBarang", idBarang);
            return "barang-not-found";
        }
    }

    @GetMapping("/barang/ubah/{idBarang}")
    public String updateBarangForm(
            @PathVariable Long idBarang,
            Model model
    ){
        BarangModel barang = barangService.getBarangById(idBarang);
        if (barang != null){
            model.addAttribute( "barang", barang);
            model.addAttribute("listTipe", tipeService.getListTipe());
            return"form-update-barang";
        } else {
            model.addAttribute( "idBarang", barang.getId());
            return "barang-not-found";
        }

    }

    @PostMapping("/barang/ubah")
    public String updateBarangSubmit(
            @ModelAttribute BarangModel barang,
            Model model
    ){
        barangService.updateBarang(barang);
        model.addAttribute("kodeBarang", barang.getKodeBarang());
        return "update-barang";
    }
}
