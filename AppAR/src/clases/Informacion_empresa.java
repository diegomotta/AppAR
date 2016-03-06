package clases;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Informacion_empresa implements Serializable {
     private String itemTitulo;
     private List<ItemDeInfos>  item_de_infos =new ArrayList<ItemDeInfos>();
     public Informacion_empresa(){
         this.itemTitulo = itemTitulo;
         this.item_de_infos = item_de_infos;
     }
    public List<ItemDeInfos> getItem_de_info() {
        return item_de_infos;
    }
    public void setItem_de_info(List<ItemDeInfos> item_de_info) {
        this.item_de_infos = item_de_info;
    }
    public String getItemTitulo() {
        return itemTitulo;
    }
    public void setItemTitulo(String itemTitulo) {
        this.itemTitulo = itemTitulo;
    }
     
 
}
