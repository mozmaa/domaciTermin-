package com.ftninformatika.modul2.restoran.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ftninformatika.modul2.restoran.model.Porudzbina;
import com.ftninformatika.modul2.restoran.model.StavkaPorudzbine;
import com.ftninformatika.modul2.restoran.web.Dostava;

@Controller
@RequestMapping("/porudzbine")
public class PorudzbinaController {

	private Dostava dostava;
	
	public PorudzbinaController(Dostava dostava) {
		this.dostava = dostava;
	}
	
	@GetMapping("")
	@ResponseBody
	public String getAll() {
		List<Porudzbina> porudzbine = new ArrayList<Porudzbina>(dostava.getPorudzbine().values());
		
		StringBuilder response = new StringBuilder();
		response.append(
				"<!DOCTYPE html>\r\n"
				+ "<html>\r\n"
				+ "  <head>\r\n"
				+ "    <meta charset=\"UTF-8\" />\r\n"
				+ "    <link rel=\"stylesheet\" type=\"text/css\" href=\"/webjars/bootstrap/css/bootstrap.min.css\" />\r\n"
				+ "    <title>Porudžbine</title>\r\n"
				+ "  </head>\r\n"
				+ "  <body>\r\n"
				+ "    <div class=\"container-fluid\">\r\n"
				+ "      <div class=\"row\">\r\n"
				+ "        <div class=\"col\">\r\n"
				+ "          <nav class=\"navbar navbar-expand navbar-dark bg-dark\">\r\n"
				+ "            <a class=\"navbar-brand\" href=\"https://enastava.ftninformatika.com\">\r\n"
				+ "              <img src=\"/images/logo.svg\" />\r\n"
				+ "            </a>\r\n"
				+ "            <div class=\"navbar-nav\">\r\n"
				+ "              <a class=\"nav-item nav-link\" href=\"/index.html\">Početna</a>\r\n"
				+ "              <a class=\"nav-item nav-link\" href=\"/restorani\">Restorani</a>\r\n"
				+ "				 <a class=\"nav-item nav-link\" href=\"/artikli\">Artikli</a>"
				+ "              <a class=\"nav-item nav-link active\" href=\"/porudzbine\">Porudžbine</a>\r\n"
				+ "              <a class=\"nav-item nav-link\" href=\"/kategorije\">Kategorije</a>\r\n"
				+ "              <a class=\"nav-item nav-link\" href=\"/korisnici\">Korisnici</a>\r\n"
				+ "            </div>\r\n"
				+ "          </nav>\r\n"
				+ "          <nav class=\"navbar navbar-expand navbar-dark bg-secondary\">\r\n"
				+ "            <div class=\"navbar-nav\">\r\n"
				+ "              <a class=\"nav-item nav-link active\" href=\"/porudzbine\">Prikaz svih</a>\r\n"
				+ "            </div>\r\n"
				+ "          </nav>\r\n"
				+ "        </div>\r\n"
				+ "      </div>\r\n"
				+ "      <div class=\"row\">\r\n"
				+ "        <div class=\"col\">\r\n"
				+ "          <table class=\"table table-striped\">\r\n"
				+ "            <thead>\r\n"
				+ "              <tr>\r\n"
				+ "                <th>Redni broj</th>\r\n"
				+ "                <th>Datum</th>\r\n"
				+ "                <th>Korisnik</th>\r\n"
				+ "                <th>Restoran</th>\r\n"
				+ "                <th>Artikli</th>\r\n"
				+ "                <th>Cena dostave</th>\r\n"
				+ "                <th>Adresa dostave</th>\r\n"
				+ "              </tr>\r\n"
				+ "            </thead>\r\n"
				+ "            <tbody class=\"table-group-divider\">\r\n"
			);
		for(int i = 0; i < porudzbine.size(); i++) {
			Porudzbina tempPor = porudzbine.get(i);
			response.append(
					"			<tr>\r\n"
					+ "				<td>" + (i + 1) + "</td>\r\n"
					+ "				<td>" + tempPor.dateToString() + "</td>\r\n"
					+ "				<td>" + tempPor.getKorisnik().getKorisnickoIme() + "</td>\r\n"
					+ "				<td>" + tempPor.getRestoran().getNaziv() + "</td>\r\n"
					+ "				<td  data-toggle=\"tooltip\" title=\"" + tempPor.getNapomena() + "\">\r\n"
					+ "					<ul>\r\n"
					);
			for (StavkaPorudzbine tempStav : tempPor.getStavkePorudzbine()) {
				response.append(
						"					<li>\r\n" +  tempStav.prikazStavke() + 	"</li>\r\n"
						);
			}
				response.append(
					"				<td class=\"text-end\">" + tempPor.getRestoran().getCenaDostave() + " RSD</td>\r\n"
					+ "				<td>" + tempPor.getKorisnik().getAdresa().AdresaBezPostanskog() + "</td>\r\n"
					+ "			</tr>\r\n"
				);
		}
		
			response.append(
				
				"            </tbody>\r\n"
				+ "          </table>\r\n"
				+ "        </div>\r\n"
				+ "      </div>\r\n"
				+ "    </div>\r\n"
				+ "  </body>\r\n"
				+ "</html>\r\n"
				+ "");
		return response.toString();
	}
}
