package com.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Currency")
public class Currency
{
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private String id;
    private String code;
    private String ChineseCodeName ;
    private String creator;
    private String modify;
    private Date createtime;
    private Date lastupdate;
    
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getChineseCodeName() {
        return ChineseCodeName;
    }
    public void setChineseCodeName(String chineseCodeName) {
        ChineseCodeName = chineseCodeName;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
	public String getCreator()
	{
		return creator;
	}
	public void setCreator( String creator )
	{
		this.creator = creator;
	}
	public String getModify()
	{
		return modify;
	}
	public void setModify( String modify )
	{
		this.modify = modify;
	}
	public Date getCreatetime()
	{
		return createtime;
	}
	public void setCreatetime( Date createtime )
	{
		this.createtime = createtime;
	}
	public Date getLastupdate()
	{
		return lastupdate;
	}
	public void setLastupdate( Date lastupdate )
	{
		this.lastupdate = lastupdate;
	}

}
