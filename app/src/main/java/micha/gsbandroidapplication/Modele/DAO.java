package micha.gsbandroidapplication.Modele;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class DAO {

    private static String urlMed = "https://ben-ndui.com/assets/GsbAndroid/index.php?dep=";
    private static final String urlDep = "https://ben-ndui.com/assets/GsbAndroid/index.php";

    public DAO() {
    }

    public static List<String> getLesDep() {
        List<String> myArray = new ArrayList<>();
        try{
            URL myURL = new URL(urlDep);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(myURL.openStream());
            Element racine = doc.getDocumentElement();
            NodeList listeDep = racine.getElementsByTagName("Departement");
            for (int i = 0; i < listeDep.getLength();i++) {
                Node departement = listeDep.item(i);
                NodeList lesProprietes = departement.getChildNodes();
                for (int j = 0; j < lesProprietes.getLength(); j++) {
                    if (lesProprietes.item(j).getNodeName().equals("num")) {
                        myArray.add(lesProprietes.item(j).getTextContent().trim());
                        break;
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return myArray;
    }

    public static List<Medecin> getLesNoms(String numDep) {
        List<Medecin> myArray = new ArrayList<>();

        try{
            URL myURL = new URL(urlMed + numDep);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(myURL.openStream());
            Element racine = doc.getDocumentElement();
            NodeList listeMed = racine.getElementsByTagName("Medecin");
            //récup des médecins
            for (int i = 0; i < listeMed.getLength();i++) {
                Node medecin = listeMed.item(i);
                NodeList lesProprietes = medecin.getChildNodes();
                // recherche du nom
                Medecin unMedcin = new Medecin();
                for (int j = 0; j < lesProprietes.getLength(); j++) {
                    if(lesProprietes.item(j).getNodeName().equals("nom")) {
                        unMedcin.setMedNom(lesProprietes.item(j).getTextContent().trim());
                    }
                    if(lesProprietes.item(j).getNodeName().equals("prenom")) {
                        unMedcin.setMedPrenom(lesProprietes.item(j).getTextContent().trim());
                    }
                    if(lesProprietes.item(j).getNodeName().equals("adresse")) {
                        unMedcin.setMedAddr(lesProprietes.item(j).getTextContent().trim());
                    }
                    if(lesProprietes.item(j).getNodeName().equals("specialite") && lesProprietes.item(j).getTextContent().trim() != "") {
                        unMedcin.setMedSpecialite(lesProprietes.item(j).getTextContent().trim());
                    }
                    if(lesProprietes.item(j).getNodeName().equals("specialite") && lesProprietes.item(j).getTextContent().trim() == "") {
                        unMedcin.setMedSpecialite("Généraliste");
                    }
                    if(lesProprietes.item(j).getNodeName().equals("tel")) {
                        unMedcin.setMedNumTel(lesProprietes.item(j).getTextContent().trim());
                    }
                }
                myArray.add(unMedcin);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return myArray;
    }

    public static List<String> getListInfo(List<Medecin> listMed){
        List<String> listInfo = new ArrayList<>();
        for(Medecin unMed : listMed){
            listInfo.add(unMed.getInfos());
        }
        return listInfo;
    }
}
