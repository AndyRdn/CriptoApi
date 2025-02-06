package org.main.criptoapi.dashBoard;

import org.main.criptoapi.histoCrypto.HistoCrypto;
import org.main.criptoapi.histoCrypto.HistoCryptoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(origins = "http://localhost:5173")
public class DashboardRestController {

    @Autowired
    private HistoCryptoService histoCryptoService;

    @GetMapping("/")
    public ApiResponse getCryptoData() {
        List<HistoCrypto> histoCryptoList = histoCryptoService.findLastValueForEachCrypto(100);
        List<HistoCrypto> actuelles = histoCryptoService.findLastValueForEachCrypto();

        ApiResponse.Data data = new ApiResponse.Data(histoCryptoList, actuelles);
        return new ApiResponse("ok", data, null);
    }
}
