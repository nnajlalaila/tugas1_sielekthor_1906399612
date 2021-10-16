package apap.tugas.sielekthor.controller;
import apap.tugas.sielekthor.model.*;
import apap.tugas.sielekthor.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PembelianController {
    @Qualifier("pembelianServiceImpl")

    @Autowired
    private PembelianService pembelianService;

    @Autowired
    private PembelianBarangService pembelianBarangService;

    @Autowired
    private BarangService barangService;

    @Autowired
    private MemberService memberService;


    @GetMapping("/pembelian")
    public String viewAllPembelian(Model model){
        List<PembelianModel> listPembelian = pembelianService.getListPembelian();
        model.addAttribute("listPembelian", listPembelian);
        return "viewall-pembelian";
    }

    @GetMapping("/pembelian/{idPembelian}")
    public String viewDetailBarang(
            @PathVariable Long idPembelian,
            Model model
    ) {
        PembelianModel pembelian = pembelianService.getPembelianById(idPembelian);
        List<PembelianBarangModel> listPembelianBarang = pembelian.getListPembelianBarang();
        if (pembelian != null){
            model.addAttribute( "pembelian", pembelian);
            model.addAttribute( "listPembelianBarang", listPembelianBarang);
            return "view-detail-pembelian";
        } else {
            model.addAttribute( "idPembelian", idPembelian);
            return "pembelian-not-found";
        }
    }


    @GetMapping("/pembelian/tambah")
    public String addPembelianForm(Model model){
        PembelianModel pembelian = new PembelianModel();

        model.addAttribute("pembelian", pembelian);
        model.addAttribute("allBarang", barangService.getListBarang());
        model.addAttribute("listMember", memberService.getListMember());
        return "form-add-pembelian";
    }


    @PostMapping(value = "/pembelian/tambah", params = {"addPembelianBarang"})
    private String addRow(
            @ModelAttribute PembelianModel pembelian,
            BindingResult bindingResult,
            Model model
    ){
        PembelianBarangModel pembelianBarang = new PembelianBarangModel();
        if (pembelian.getListPembelianBarang() == null) {
            pembelian.setListPembelianBarang(new ArrayList<PembelianBarangModel>());
        }
        pembelian.getListPembelianBarang().add(pembelianBarang);
        model.addAttribute("pembelian", pembelian);
        model.addAttribute("pembelianBarang", pembelianBarang);
        model.addAttribute("allBarang", barangService.getListBarang());
        model.addAttribute("listMember", memberService.getListMember());
        return "form-add-pembelian";
    }

    @PostMapping(value="/pembelian/tambah", params = {"deleteRow"})
    private String deleteRow(
            @ModelAttribute PembelianModel pembelian,
            final BindingResult bindingResult, final HttpServletRequest req,
            Model model
    ){

        final Integer rowId = Integer.valueOf(req.getParameter("deleteRow"));
        List<PembelianBarangModel> listPembelianBarang = pembelianBarangService.getPembelianBarangList();
        pembelian.getListPembelianBarang().remove(rowId.intValue());
        model.addAttribute("pembelian", pembelian);
        model.addAttribute("listPembelianBarang", pembelian.getListPembelianBarang());
        model.addAttribute("listMember", memberService.getListMember());
        model.addAttribute("allBarang", barangService.getListBarang());
        return "form-add-pembelian";
    }


    @PostMapping(value="/pembelian/tambah/", params = {"save"})
    private String save(@ModelAttribute PembelianModel pembelian, Model model) {
        pembelian.setTanggalPembelian(new Date());
        Integer total = 0;
        String noInvoice = pembelianService.generateNoInvoice(pembelian);
        pembelian.setNoInvoice(noInvoice);
        for (PembelianBarangModel pembelianBarang : pembelian.getListPembelianBarang()) {
            Date tglPembelian = pembelian.getTanggalPembelian();
            LocalDate localDate = tglPembelian.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            Long jumlahGaransi = Long.valueOf(pembelianBarang.getBarang().getJumlahGaransi());
            System.out.println(jumlahGaransi);
            LocalDate tanggalGaransi = localDate.plusDays(jumlahGaransi);
            Date tanggalGaransiFix = Date.from(tanggalGaransi.atStartOfDay(ZoneId.systemDefault()).toInstant());
            pembelianBarang.setTanggalGaransi(tanggalGaransiFix);
        }

        for (PembelianBarangModel pembelianBarang : pembelian.getListPembelianBarang()) {
            total += pembelianBarang.getQuantity () * pembelianBarang.getBarang().getHargaBarang();
        }
        pembelian.setTotal(total);
        pembelianService.addPembelian(pembelian);
        for (PembelianBarangModel pembelianBarang : pembelian.getListPembelianBarang()) {
            PembelianBarangModel newPB = new PembelianBarangModel();
            newPB.setQuantity(pembelianBarang.getQuantity());
            newPB.setPembelian(pembelian);
            newPB.setBarang(pembelianBarang.getBarang());
            newPB.setTanggalGaransi(pembelianBarang.getTanggalGaransi());
            newPB.setPembelian(pembelianService.getPembelianById(pembelian.getId()));
            pembelianBarangService.addPembelianBarang(newPB);
        }
            model.addAttribute("pembelian", pembelian);
        return "add-pembelian";
    }

    @PostMapping("/pembelian/hapus")
    private String hapusPembelian(
            @ModelAttribute PembelianModel pembelian,
            Model model
    ){
        pembelianService.restokBarang(pembelian);
        pembelianService.deletePembelian(pembelian);
        model.addAttribute("noInvoice", pembelian.getNoInvoice());
        model.addAttribute("listPembelianBarang", pembelian.getListPembelianBarang());
        return "delete-pembelian";
    }

    @GetMapping("/cari/pembelian")
    public String cariPembelian(
            @RequestParam(required = false, value = "idMember") Long idMember,
            @RequestParam(required = false,value = "isCicilan") Boolean isCicilan,
            Model model
    ){
        System.out.println("MASUK CONTROLLER");
        List<PembelianModel> hasil = new ArrayList<>();
//        MemberModel member = memberService.getMemberById(idMember);
        List<MemberModel> listMember = memberService.getListMember();
//        List<PembelianModel> listPembelian = pembelianService.getListPembelian();

        if ( idMember != null && isCicilan != null) {
            System.out.println("dUADAU TIDAK NULL");
            hasil = pembelianService.cariPembelian(idMember, isCicilan);
            if (hasil.size() == 0){
                String messages = "Hasil tidak ditemukan";
                model.addAttribute("message", messages );
            }
            model.addAttribute("listPembelian", hasil);
            model.addAttribute("listMember",listMember);
           ;
            return "cari-pembelian";
        }

        else if ( idMember != null && isCicilan == null) {
            hasil = pembelianService.cariPembelianIdMember(idMember);
            if (hasil.size() == 0){
                String messages = "Hasil tidak ditemukan";
                model.addAttribute("message", messages );
            }
            model.addAttribute("listPembelian", hasil);
            model.addAttribute("listMember",listMember);
            System.out.println("MEMBER TDK NULL");
            return "cari-pembelian";
        }

        else if ( idMember == null && isCicilan != null) {
            hasil = pembelianService.cariPembelianIsCash(isCicilan);
            if (hasil.size() == 0){
                String messages = "Hasil tidak ditemukan";
                model.addAttribute("message", messages );
            }
            model.addAttribute("listPembelian", hasil);
            model.addAttribute("listMember",listMember);
            System.out.println("IS CICILAN");
            return "cari-pembelian";
        }
            model.addAttribute("listPembelian", hasil);
            model.addAttribute("listMember",listMember);
            System.out.println("----------");
            return "cari-pembelian";


    }




//    @GetMapping(value = "/bonus/cari/member/paling-banyak")
//    public String cariMember(Model model) {
//        List<PembelianBarangModel> listPembelianBarang = pembelianBarangService.getPembelianBarangList();
//        List<PembelianBarangModel> listPilothu = pembelianBarangService.pilotBulanIni(listPilotPenerbangan);
//        List<PilotModel> listPilot = pilotPenerbanganService.getListPilot(listPilothu);
//        model.addAttribute("listPilot", listPilot);
//        return "cari-pilotbulanini";
//
//    }
}


