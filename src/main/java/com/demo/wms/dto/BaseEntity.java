package com.demo.wms.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
 
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class BaseEntity {
 
    @Column(name = "creation_time", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date creationTime;
 
    @Column(name = "modification_time")
	@Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date modificationTime;
    
    @PrePersist
    public void prePersist() {
        Date now = new Date();
        this.creationTime = now;
        this.modificationTime = now;
    }
     
    @PreUpdate
    public void preUpdate() {
        this.modificationTime = new Date();
    }

	public Date getCreationTime() {
		return creationTime;
	}

	public Date getModificationTime() {
		return modificationTime;
	}
    
}
