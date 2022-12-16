package com.example.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.model.CsvTData;
import com.example.service.ListDataService;

@Controller
@RequestMapping("/recipt")
public class CsvController{
	
	@Autowired
	private ListDataService listDataService;
	
    //@Autowired
    //DownloadHelper downloadHelper;
	
	@GetMapping("/csv")
	public String getCsvView() {	
	    return "recipt/csv";
	}
	
	@PostMapping(value = "/csv/upload", params = "upload_file")
	public String updateUser(@RequestParam("file") MultipartFile uploadFile) {
		// 現在のT_DATAをダウンロード
		//try {
			//download();
		//} catch (IOException e1) {
			// TODO 自動生成された catch ブロック
		//	e1.printStackTrace();
		//}
		
		Integer i = 0;
		Date today = new Date();
		List<CsvTData> dataList = new ArrayList<CsvTData>();
		 try (BufferedReader br = new BufferedReader(new InputStreamReader(uploadFile.getInputStream(), StandardCharsets.UTF_8))){
		      String line;
		      while ((line = br.readLine()) != null) {
		    	if(i == 0) {
		    		// ヘッダーは飛ばす
		    		i++;
		    		continue;
		    	}    	
		    	CsvTData tData = new CsvTData();
		        final String[] split = line.split(",");
		        tData.setReciptId(split[0].trim());
		        SimpleDateFormat sdFormat = new SimpleDateFormat("MM/dd/yyyy");
		        sdFormat.setLenient(false);
		        String strBuyDate = split[1].trim();
		        Date buyDate = null;
				try {
					buyDate = sdFormat.parse(strBuyDate);
				} catch (ParseException e) {
					
				}
		        tData.setBuyDate(buyDate);
		        tData.setCategoryName(split[2].trim());
		        tData.setItemName(split[5].trim());
		        tData.setShopName(split[6].trim());
		        String strMoney = split[7].trim();
		        try {
		        	BigDecimal bigDecimal = new BigDecimal(strMoney);
		        	tData.setSpentMoney(bigDecimal);
		        } catch(Exception e) {
		        	continue;
		        }
		        tData.setInputDate(today);
		        tData.setUpdateDate(today);
		        tData.setDelFlg(false);
		        dataList.add(tData);
		        i++;
		        System.out.println(tData);
		      }
		    } catch (IOException e) {
		      throw new RuntimeException("ファイルが読み込めません", e);
		    }	
		Integer cnt = i - 1;
		// 登録開始
		for(int j = 0; j < dataList.size(); j++) {
			CsvTData tData = dataList.get(j);
			tData.setItemId(Integer.toString(cnt));
			listDataService.insertListData(tData);
			cnt--;
		}		 
		// ユーザー一覧画面にリダイレクト
		return "redirect:/recipt/csv";
	}
	/*
	public String getCsvText() {
		CsvMapper mapper = new CsvMapper();
        //文字列にダブルクオートをつける
        mapper.configure(CsvGenerator.Feature.ALWAYS_QUOTE_STRINGS, true);
        //ヘッダをつける
        CsvSchema schema = mapper.schemaFor(TData.class).withHeader();
		
		List<TData> allTData = listDataService.getAllTData();
		try {
			return mapper.writer(schema).writeValueAsString(allTData);
		} catch (JsonProcessingException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			return "";
		}
	}
	
    @RequestMapping(value = "/csv/output", method = RequestMethod.POST)
    public ResponseEntity<byte[]> download() throws IOException {
        HttpHeaders headers = new HttpHeaders();
        downloadHelper.addContentDisposition(headers, "日本語ファイル名.csv");
        return new ResponseEntity<>(getCsvText().getBytes("MS932"), headers, HttpStatus.OK);
    }*/
}