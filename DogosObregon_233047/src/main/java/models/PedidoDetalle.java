/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author David TL
 */
@Entity
public class PedidoDetalle implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(optional =  false)
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;
    
    @ManyToOne(optional =  false)
    @JoinColumn(name = "hotdog_id")
    private HotDog hotdog;

    private int cantidad;
    
    @Column (name = "precio_venta")
    private BigDecimal precioVenta;
    
    private BigDecimal subTotal;

    public PedidoDetalle() {
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public HotDog getHotdog() {
        return hotdog;
    }

    public void setHotdog(HotDog hotdog) {
        this.hotdog = hotdog;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(BigDecimal precioVenta) {
        this.precioVenta = precioVenta;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public PedidoDetalle(Long id, Pedido pedido, HotDog hotdog, int cantidad, BigDecimal precioVenta, BigDecimal subTotal) {
        this.id = id;
        this.pedido = pedido;
        this.hotdog = hotdog;
        this.cantidad = cantidad;
        this.precioVenta = precioVenta;
        this.subTotal = subTotal;
    }
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PedidoDetalle)) {
            return false;
        }
        PedidoDetalle other = (PedidoDetalle) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.PedidoDetalle[ id=" + id + " ]";
    }
    
}
