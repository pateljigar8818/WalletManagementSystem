package com.demo.wms.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name="wallet")
@EntityListeners(AuditingEntityListener.class)
@NamedQuery(name="Wallet.findAll", query="SELECT w FROM Wallet w")
public class Wallet extends BaseEntity{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="wallet_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int walletId;
	
	@Column(name="is_web_ui_active")
	private boolean webInterfaceActive;
	
	@Column(name="is_wallet_activated")
	private boolean active;
	
	@Column(name="is_deleted")
	private boolean deleted;
	
	//bi-directional one-to-one association to User
	@OneToOne
	@JoinColumn(name="user_id")
	private User user;
	
	//bi-directional many-to-one association to CustRequest
	@OneToMany(mappedBy="wallet", cascade=CascadeType.ALL, orphanRemoval=true)
	private List<Cards> cards;

	public int getWalletId() {
		return walletId;
	}

	public void setWalletId(int walletId) {
		this.walletId = walletId;
	}

	public boolean isWebInterfaceActive() {
		return webInterfaceActive;
	}

	public void setWebInterfaceActive(boolean webInterfaceActivation) {
		this.webInterfaceActive = webInterfaceActivation;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean activation) {
		this.active = activation;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.deleted = isDeleted;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Cards> getCards() {
		return cards;
	}

	public void setCards(List<Cards> cards) {
		this.cards = cards;
	}
	
	public Cards addCards(Cards cards) {
		if(getCards()==null){
			this.cards=new ArrayList<Cards>();
		}
		getCards().add(cards);
		cards.setWallet(this);

		return cards;
	}

	public Cards removeCards(Cards cards) {
		getCards().remove(cards);
		cards.setWallet(null);

		return cards;
	}


}
