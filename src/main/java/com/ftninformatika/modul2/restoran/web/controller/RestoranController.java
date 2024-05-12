package com.ftninformatika.modul2.restoran.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ftninformatika.modul2.restoran.model.Artikal;
import com.ftninformatika.modul2.restoran.model.Kategorija;
import com.ftninformatika.modul2.restoran.model.Restoran;
import com.ftninformatika.modul2.restoran.web.Dostava;

@Controller
@RequestMapping("/restorani")
public class RestoranController {

	private Dostava dostava;

	public RestoranController(Dostava dostava) {
		this.dostava = dostava;
	}

	@GetMapping("")
	@ResponseBody
	public String getAll() {
		List<Restoran> restorani = new ArrayList<>(dostava.getRestorani().values());
		
		StringBuilder response = new StringBuilder();
		response.append(
				"<!DOCTYPE html>\r\n"
				+ "<html>\r\n"
				+ "  <head>\r\n"
				+ "    <meta charset=\"UTF-8\" />\r\n"
				+ "    <link rel=\"stylesheet\" type=\"text/css\" href=\"/webjars/bootstrap/css/bootstrap.min.css\" />\r\n"
				+ "    <title>Restorani</title>\r\n"
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
				+ "              <a class=\"nav-item nav-link active\" href=\"/restorani\">Restorani</a>\r\n"
				+ "				 <a class=\"nav-item nav-link\" href=\"/artikli\">Artikli</a>"
				+ "              <a class=\"nav-item nav-link\" href=\"/porudzbine\">Porudžbine</a>\r\n"
				+ "              <a class=\"nav-item nav-link\" href=\"/kategorije\">Kategorije</a>\r\n"
				+ "              <a class=\"nav-item nav-link\" href=\"/korisnici\">Korisnici</a>\r\n"
				+ "            </div>\r\n"
				+ "          </nav>\r\n"
				+ "          <nav class=\"navbar navbar-expand navbar-dark bg-secondary\">\r\n"
				+ "            <div class=\"navbar-nav\">\r\n"
				+ "              <a class=\"nav-item nav-link active\" href=\"/restorani\">Prikaz svih</a>\r\n"
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
				+ "                <th>Naziv</th>\r\n"
				+ "                <th>Kategorija</th>\r\n"
				+ "                <th>Artikli</th>\r\n"
				+ "                <th>Cena dostave</th>\r\n"
				+ "                <th>Adresa</th>\r\n"
				+ "              </tr>\r\n"
				+ "            </thead>\r\n"
				+ "            <tbody class=\"table-group-divider\">\r\n"
			);
		for(int i = 0; i < restorani.size(); i++) {
			Restoran tempRest = restorani.get(i);
			response.append(
					"				<tr>\r\n"
					+ "					<td>" + (i + 1) + "</td>\r\n"
					+ " 				<td>" + tempRest.getNaziv() + "</td>\r\n"
					+ "					<td>\r\n"
					+ "						<ul>\r\n"
				);
			for(Kategorija tempKat : tempRest.getKategorije()) {
				response.append(
						"						<li>" + tempKat.getNaziv() + "</li>\r\n"
					);
			}
			response.append(
					"						</ul>\r\n"
					+ "					</td>\r\n"
					+ "					<td>\r\n"
					+ "						<ul>"
					);
			for(Artikal tempArt : tempRest.getArtikli()) {
				response.append(
					"							<li>" + tempArt.getNaziv() + "</li>\r\n");
			}
			response.append(
					"						</ul>\r\n"
					+ "					</td>\r\n"
					+ "\r\n"
					+ "					<td class=\"text-end\">" + tempRest.getCenaDostave() + " RSD </td>\r\n"
					+ "					<td>" + tempRest.getAdresa().AdresaSaPostanskim() + "</td>\r\n"
					+ "\r\n");
		}
		response.append(
				               
				"              </tr>\r\n"
				+ "            </tbody>\r\n"
				+ "          </table>\r\n"
				+ "        </div>\r\n"
				+ "      </div>\r\n"
				+ "    </div>\r\n"
				+ "  </body>\r\n"
				+ "</html>");
		return response.toString();
	}
}
