/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

/**
 *
 * @author David TL
 */
@Entity
public class Pedido implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime fecha;
    
    @Enumerated(EnumType.STRING)
    @Column (name = "met.pago", nullable = false)
    private MetodoPago metodoPago;
    
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
/*
    @ManyToMany
    @JoinTable(name = "pedido_hotdog", joinColumns =
        @JoinColumn (name = "pedido_id"),
        inverseJoinColumns = @JoinColumn (name = "hotdog_id")
    )
*/
    @OneToMany(mappedBy = "pedido")
    private Set<PedidoDetalle> detalles;

    public Pedido(Long id, LocalDateTime fecha, MetodoPago metodoPago, Cliente cliente, Set<PedidoDetalle> detalles, Set<HotDog> hotdogs) {
        this.id = id;
        this.fecha = fecha;
        this.metodoPago = metodoPago;
        this.cliente = cliente;
        this.detalles = detalles;
        this.hotdogs = hotdogs;
    }

    public Set<PedidoDetalle> getDetalles() {
        return detalles;
    }

    public void setDetalles(Set<PedidoDetalle> detalles) {
        this.detalles = detalles;
    }
    
private Set<HotDog> hotdogs;
    
    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }


    public Set<HotDog> getHotdogs() {
        return hotdogs;
    }

    public void setHotdogs(Set<HotDog> hotdogs) {
        this.hotdogs = hotdogs;
    }

    public Pedido(Long id, LocalDateTime fecha, MetodoPago metodoPago, Cliente cliente, Set<HotDog> hotdogs) {
        this.id = id;
        this.fecha = fecha;
        this.metodoPago = metodoPago;
        this.cliente = cliente;
        this.hotdogs = hotdogs;
    }

    public Pedido() {
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
        if (!(object instanceof Pedido)) {
            return false;
        }
        Pedido other = (Pedido) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.Pedido[ id=" + id + " ]";
    }
    
}
