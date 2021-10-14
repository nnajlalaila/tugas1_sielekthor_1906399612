package apap.tugas.sielekthor.controller;
import apap.tugas.sielekthor.model.*;
import apap.tugas.sielekthor.service.*;
import jdk.incubator.foreign.MappedMemorySegment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
        PembelianBarangModel pembelianBarang = new PembelianBarangModel();
//        BarangModel barang = new BarangModel();
        List<PembelianBarangModel> listPembelianBarang = new ArrayList<>();
        Date tanggalPembelian = new Date();
        pembelian.setTanggalPembelian(tanggalPembelian);

        pembelianBarang.setPembelian(pembelian);
        pembelian.setListPembelianBarang(listPembelianBarang);
//        barang.setListPembelianBarang(listPembelianBarang);
//
//        System.out.println("DI GET MEPING");
//        System.out.println(pembelian);
//        for (PembelianBarangModel i:pembelian.getListPembelianBarang()
//        ) {
//            System.out.println("DALAM LOOP");
//            System.out.println(pembelian);
//            System.out.println(i);
//            System.out.println(i.getBarang());
//        }

        model.addAttribute("pembelian", pembelian);
        model.addAttribute("pembelianBarang", pembelianBarang);
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
//        System.out.println(pembelian);
//        System.out.println("DI TAMBAH SEBELUM");
//        for (PembelianBarangModel i:pembelian.getListPembelianBarang()
//        ) {
//            System.out.println(i);
////            System.out.println(i.getBarang());
//        }
        PembelianBarangModel pembelianBarang = new PembelianBarangModel();
        if (pembelian.getListPembelianBarang() == null) {
            pembelian.setListPembelianBarang(new ArrayList<PembelianBarangModel>());
        }
//        Date tanggalPembelian = new Date();
//        pembelian.setTanggalPembelian(tanggalPembelian);
        for (PembelianBarangModel i : pembelian.getListPembelianBarang()) {
            Integer jumlahGaransi= i.getBarang().getJumlahGaransi();
//            Date d = new Date(i.getPembelian(). + 86400000* jumlahGaransi);
//            i.setTanggalGaransi(d);
            pembelianBarangService.addPembelianBarang(i);
        }
        pembelianBarang.setPembelian(pembelian);
        pembelian.getListPembelianBarang().add(pembelianBarang);
        System.out.println("DI TAMBAH");
        for (PembelianBarangModel i: pembelian.getListPembelianBarang()
             ) {
            System.out.println(i);
            System.out.println(i.getBarang());
            System.out.println(i.getPembelian());
        }
//        Integer totalHarga = pembelianService.jumlah(pembelianBarangHash);
//        pembelian.setNoInvoice(noInvoice);
//        pembelian.setTanggalPembelian(LocalDate.now());
//        pembelian.setTotal(totalHarga);
//        LocalDate tanggalPembelian = pembelian.getTanggalPembelian().plusDays(barang.getJumlahGaransi());
//        pembelianBarang.setTanggalGaransi(tanggalPembelian);

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
        pembelian.getListPembelianBarang().remove(rowId.intValue());
        model.addAttribute("pembelian", pembelian);
        model.addAttribute("listMember", memberService.getListMember());
        model.addAttribute("allBarang", barangService.getListBarang());
        return "form-add-pembelian";
    }


    @PostMapping(value="/pembelian/tambah/", params = {"save"})
    private String save(@ModelAttribute PembelianModel pembelian, Model model) {
        System.out.println(pembelian);
        System.out.println("DI SAVE");


        for (PembelianBarangModel pembelianBarang : pembelian.getListPembelianBarang()) {
            Integer total = pembelianBarang.getQuantity() * pembelianBarang.getBarang().getHargaBarang();
            pembelianBarang.getPembelian().setTotal(total);
            pembelianBarang.setPembelian(pembelian);
        }

        String noInvoice = pembelianService.getNoInvoice(pembelian);
        pembelian.setNoInvoice(noInvoice);
        pembelianService.addPembelian(pembelian);

//        for (PembelianBarangModel i : pembelian.getListPembelianBarang()) {
//           pembelianBarangService.addPembelianBarang(i);
//        }

        for (PembelianBarangModel i: pembelian.getListPembelianBarang()
        ) {
            System.out.println(i);
            System.out.println(i.getPembelian());
            System.out.println(i.getBarang());
            System.out.println(i.getBarang().getKodeBarang());
            System.out.println(i.getBarang().getHargaBarang());
        }


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

    ///cari/pembelian?idMember={idMember}&tipePembayaran={isCicilan}

    @GetMapping("/cari/pembelian?idMember={idMember}&tipePembayaran={isCicilan}")
    public String cariPembelian(
            @RequestParam(required = false, value = "idMember") Long idMember,
            @RequestParam(required = false,value = "isCicilan") Boolean isCicilan,
            Model model
    ){
       return "home";

    }
}


