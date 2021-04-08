/*
 * 创建日期 2005-8-18
 *
 * TODO 要更改此生成的文件的模板，请转至
 * 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */
package com.xiaobo.springboot2.entity;

import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @author lei_peng
 *
 * TODO 要更改此生成的类型注释的模板，请转至
 * 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */
public class UserForm {

    /**
     * @return 返回 oldPassword。
     */
    public final String getOldPassword()
    {
        return oldPassword;
    }
    /**
     * @param oldPassword 要设置的 oldPassword。
     */
    public final void setOldPassword(String oldPassword)
    {
        this.oldPassword = oldPassword;
    }
    /**
     * @return 返回 type。
     */
    public final String getType()
    {
        return type;
    }
    /**
     * @param type 要设置的 type。
     */
    public final void setType(String type)
    {
        this.type = type;
    }
    /**
     * @return
     */
    public String getLastModifyUser()
    {
        return baseInfo.getLastModifyUser();
    }
    /**
     * @param lastModifyUser
     */
    public void setLastModifyUser(String lastModifyUser)
    {
        baseInfo.setLastModifyUser(lastModifyUser);
    }
	public UserForm()
	{
		lastModifyPasswordTime = new Timestamp(System.currentTimeMillis());
		baseInfo = new BaseInfo();
		failedLoginTimes = new Integer(0);
		resetFields();
	}
	
    private void resetFields()
	{
        baseInfo.reset();
        
		password = null;
		oldPassword = "";
		
		minIp = "";//"192.168.1.1";
		maxIp = "";//"192.168.1.100";

        maxOnline = 1;
        allowIps = null;
		
		liveTime = new Integer(5); // default 5 minutes
		beginTime = new Date(System.currentTimeMillis());
		expireTime = new Date(beginTime.getYear()+1,beginTime.getMonth(),beginTime.getDate()-1,beginTime.getHours(),beginTime.getMinutes(),beginTime.getSeconds());
		
		//System.currentTimeMillis() + 60*60*24*365*30000);
		//new Date(System.currentTimeMillis() + 1000 * 86400 * 30);
		
		caption = "";//"普通职员";
		phone = "";//"+86-010－88888888";
		department = "";//"生产部";
		fax = "";//"+86-010－88888888";
		status = false;
		type = "";
	}

	/* （非 Javadoc）
	 * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	/*public void reset(ActionMapping arg0, HttpServletRequest arg1)
	{
		super.reset(arg0, arg1);
		resetFields();
	}*/
	/* （非 Javadoc）
	 * @see org.apache.struts.action.ActionForm#validate(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	/*public ActionErrors validate(ActionMapping arg0, HttpServletRequest arg1) {
		// TODO 自动生成方法存根
		return super.validate(arg0, arg1);
	}*/
    private Set decodeRoles(String urs)
    {
        Set userRoleIds = new HashSet();
        for (int i = 0; i < urs.length(); )
        {
            int j = i;
            for (; j < urs.length(); ++j)
                if (urs.charAt(j) == ',') break;
            userRoleIds.add(new Integer(urs.substring(i, j)));
            i = j + 1;
        }
        return userRoleIds;
    }
    /*public void setRolesByString(String urs)
    {
        Set userRoleIds = decodeRoles(urs);
        Set userRoles = new HashSet();
        for (Iterator iter = userRoleIds.iterator(); iter.hasNext(); )
        {
            Integer id = (Integer)iter.next();
            AuthRole ar = new AuthRole();
            ar.setId(id);
            userRoles.add(ar);
        }
        this.setRoles(userRoles);
    }
	public void setByEjb(AuthAccount data)
	{
	    try {
	    	baseInfo.setCreate(data.isCreate());
	    	baseInfo.setDelete(data.isDelete());
	    	baseInfo.setExecute(data.isExcute());
	    	baseInfo.setRead(data.isRead());
	    	baseInfo.setWrite(data.isWrite());
            baseInfo.setWrite(data.isWrite());
            baseInfo.setActions(data.getActions());
	    	this.setId(data.getID());
	    	this.setLoginName(data.getName());
	    	this.setPassword(data.getPasswd());
	    	this.setDescription(data.getDescription());
	    	this.setRoles(data.getRoles()); // data.getRoles() 返回 AuthRoles 的对象集合
	    	if (data.getLateLoginTime() != null)
	    	    this.setLastLoginTime(new Timestamp(data.getLateLoginTime().longValue()));
	    	if (data.getCreateTime() != null)
	    	    this.setCreationTime(new Timestamp(data.getCreateTime().longValue()));
	    	if (data.getExpireTime() != null)
	    	    this.setExpireTime(new Timestamp(data.getExpireTime().longValue()));
	    	if (data.getLiveTime() != null)
	    	    this.setLiveTime(new Integer(data.getLiveTime().intValue()));
	    	if (data.getLateModifyTime() != null)
	    	    this.setLastModifyTime(new Timestamp(data.getLateModifyTime().longValue()));
	    	this.setStatus(data.getEnable().intValue() != 0);
	    	this.setLastModifyUser(data.getLateModifyUser());
	    	this.setCreator(data.getCreateUser());
	    	this.setCreationIp(BaseInfo.toIpString(data.getCreateIP()));
	    	
	    	this.setEmail(data.getEmail());
	    	this.setDepartment(data.getDepartment());
	    	this.setCaption(data.getPosition());
	    	this.setPhone(data.getTele());
	    	this.setFax(data.getFax());
	    	this.setUserName(data.getUserName());
	    	
	    	if (data.getPwdModifiedTIME() != null)
	    	    this.setLastModifyPasswordTime(new Timestamp(data.getPwdModifiedTIME().longValue()));

	    	this.setLastModifyIp(BaseInfo.toIpString(data.getLateModifyIP()));
	    	this.setLastLoginIp(BaseInfo.toIpString(data.getLateLoginIP()));
	    	this.setMinIp(BaseInfo.toIpString(data.getValidIPStart()));
	    	this.setMaxIp(BaseInfo.toIpString(data.getValidIPEnd()));
	    	this.setType(data.getUsertype());

	    	this.setMaxOnline(data.getMaxOnline());
	    	this.setAllowIps(data.getAllowIps());

	    } catch (UnknownHostException e) {
	        e.printStackTrace();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	public void createUser(SmAuthAccount data)
	{
        baseInfo.setCreate(data.isCreate());
        baseInfo.setDelete(data.isDelete());
        baseInfo.setExecute(data.isExcute());
        baseInfo.setRead(data.isRead());
        baseInfo.setWrite(data.isWrite());
        baseInfo.setWrite(data.isWrite());
        baseInfo.setActions(data.getActions());
        this.setId(data.getAccountId());
        this.setLoginName(data.getName());
        this.setPassword(data.getPasswd());
        this.setDescription(data.getDescription());
        this.setRoleId(data.getRoleId());
        this.setRoleName(data.getRoleName());
//	    	this.setRoles(data.getRoles()); // data.getRoles() 返回 AuthRoles 的对象集合
        if (data.getLateLoginTime() != null)
            this.setLastLoginTime(new Timestamp(data.getLateLoginTime().longValue()));
        if (data.getCreateTime() != null)
            this.setCreationTime(new Timestamp(data.getCreateTime().longValue()));
        if (data.getExpireTime() != null)
            this.setExpireTime(new Timestamp(data.getExpireTime().longValue()));
        if (data.getLiveTime() != null)
            this.setLiveTime(new Integer(data.getLiveTime().intValue()));
        if (data.getLateModifyTime() != null)
            this.setLastModifyTime(new Timestamp(data.getLateModifyTime().longValue()));
        this.setStatus(data.getEnable().intValue() != 0);
        this.setLastModifyUser(data.getLateModifyUser());
        this.setCreator(data.getCreateUser());
        this.setCreationIp(data.getCreateIP()+"");

        this.setEmail(data.getEmail());
        this.setDepartment(data.getDepartment());
        this.setCaption(data.getPosition());
        this.setPhone(data.getTele());
        this.setFax(data.getFax());
        this.setUserName(data.getUserName());

        if (data.getPwdModifiedTIME() != null)
            this.setLastModifyPasswordTime(new Timestamp(data.getPwdModifiedTIME().longValue()));

        this.setLastModifyIp(data.getLateModifyIP()+"");
        this.setLastLoginIp(data.getLateLoginIP()+"");
        this.setMinIp(data.getValidIPStart()+"");
        this.setMaxIp(data.getValidIPEnd()+"");
        this.setType(data.getUsertype());

        this.setMaxOnline(data.getMaxOnline());
        this.setAllowIps(data.getAllowIps());
	}
	public void queryEjb(AuthAccount data)
	{
	    if (this.getId() != null)
	        data.setID(this.getId());
    	data.setName(this.getLoginName());
    	data.setPasswd(this.getPassword());
    	data.setDescription(this.getDescription());
    	data.setRoles(this.getRoles()); // data.setRoles() 接受 RoleId 的集合
    	data.setExpireTime(BigInteger.valueOf(this.getExpireTime().getTime()));
    	data.setEnable(new Integer(this.getStatus() ? 1 : 0));

    	data.setCreateUser(this.getCreator());
    //	data.setLateModifyUser(this.getLastModifyUser());
    //	data.setLateModifyTime(BigInteger.valueOf(this.getLastModifyTime().getTime()));
    	if (this.getLastLoginTime() != null)
    	    data.setLateLoginTime(BigInteger.valueOf(this.getLastLoginTime().getTime()));
    	if (this.getCreationTime() != null)
    	    data.setCreateTime(BigInteger.valueOf(this.getCreationTime().getTime()));
    	if (this.getExpireTime() != null)
    	    data.setExpireTime(BigInteger.valueOf(this.getExpireTime().getTime()));
    	if (this.getLiveTime() != null)
    	    data.setLiveTime(BigInteger.valueOf(this.getLiveTime().intValue()));

    	data.setUsertype(this.getType());
    	data.setEmail(this.getEmail());
    	data.setDepartment(this.getDepartment());
    	data.setPosition(this.getCaption());
    	data.setTele(this.getPhone());
    	data.setFax(this.getFax());
    	data.setUserName(this.getUserName());
//    	data.setPwdModifiedTIME(BigInteger.valueOf(this.getLastModifyPasswordTime().getTime()));

        data.setMaxOnline(this.getMaxOnline());
        data.setAllowIps(this.getAllowIps());

    	try {
        	data.setValidIPStart(BaseInfo.toIpNumber(this.getMinIp()));
        	data.setValidIPEnd(BaseInfo.toIpNumber(this.getMaxIp()));
            data.setCreateIP(BaseInfo.toIpNumber(this.getCreationIp()));
        	data.setLateLoginIP(BaseInfo.toIpNumber(this.getLastLoginIp()));
        	data.setLateModifyIP(BaseInfo.toIpNumber(this.getLastModifyIp()));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
	}*/
    /**
     * @return 返回 baseInfo。
     */
    public final BaseInfo getBaseInfo()
    {
        return baseInfo;
    }
    /**
     * @param baseInfo 要设置的 baseInfo。
     */
    public final void setBaseInfo(BaseInfo baseInfo)
    {
        this.baseInfo = baseInfo;
    }
    /**
     * @return 返回 beginTime。
     */
    public final Date getBeginTime()
    {
        return beginTime;
    }
    /**
     * @param beginTime 要设置的 beginTime。
     */
    public final void setBeginTime(Timestamp beginTime)
    {
        this.beginTime = beginTime;
    }
    /**
     * @return 返回 department。
     */
    public final String getDepartment()
    {
        return department;
    }
    /**
     * @param department 要设置的 department。
     */
    public final void setDepartment(String department)
    {
        this.department = department;
    }
    /**
     * @return 返回 expireTime。
     */
    public final Date getExpireTime()
    {
        return expireTime;
    }
    /**
     * @param expireTime 要设置的 expireTime。
     */
    public final void setExpireTime(Timestamp expireTime)
    {
        this.expireTime = expireTime;
    }
    /**
     * @return 返回 failedLoginTimes。
     */
    public final Integer getFailedLoginTimes()
    {
        return failedLoginTimes;
    }
    /**
     * @param failedLoginTimes 要设置的 failedLoginTimes。
     */
    public final void setFailedLoginTimes(Integer failedLoginTimes)
    {
        this.failedLoginTimes = failedLoginTimes;
    }
    /**
     * @return 返回 fax。
     */
    public final String getFax()
    {
        return fax;
    }
    /**
     * @param fax 要设置的 fax。
     */
    public final void setFax(String fax)
    {
        this.fax = fax;
    }
    /**
     * @return 返回 lastLoginTime。
     */
    public final Date getLastLoginTime()
    {
        return lastLoginTime;
    }
    /**
     * @param lastLoginTime 要设置的 lastLoginTime。
     */
    public final void setLastLoginTime(Timestamp lastLoginTime)
    {
        this.lastLoginTime = lastLoginTime;
    }
    /**
     * @return 返回 lastModifyPasswordTime。
     */
    public final Date getLastModifyPasswordTime()
    {
        return lastModifyPasswordTime;
    }
    /**
     * @param lastModifyPasswordTime 要设置的 lastModifyPasswordTime。
     */
    public final void setLastModifyPasswordTime(Timestamp lastModifyPasswordTime)
    {
        this.lastModifyPasswordTime = lastModifyPasswordTime;
    }
    /**
     * @return 返回 liveTime。
     */
    public final Integer getLiveTime()
    {
        return liveTime;
    }
    /**
     * @param liveTime 要设置的 liveTime。
     */
    public final void setLiveTime(Integer liveTime)
    {
        this.liveTime = liveTime;
    }
    /**
     * @return 返回 maxIP。
     */
    public final String getMaxIp()
    {
        return maxIp;
    }
    /**
     * @param maxIP 要设置的 maxIP。
     */
    public final void setMaxIp(String maxIp)
    {
        try {
            InetAddress.getByName(maxIp);
            this.maxIp = maxIp;
        } catch (UnknownHostException e) {
            this.maxIp = maxIp;
        }
    }
    /**
     * @return 返回 minIP。
     */
    public final String getMinIp()
    {
        return minIp;
    }
    /**
     * @param minIP 要设置的 minIP。
     */
    public final void setMinIp(String minIp)
    {
        this.minIp = minIp;
    }
    /**
     * @return 返回 password。
     */
    public final String getPassword()
    {
        return password;
    }
    /**
     * @param password 要设置的 password。
     */
    public final void setPassword(String password)
    {
        this.password = password;
    }
    /**
     * @return 返回 phone。
     */
    public final String getPhone()
    {
        return phone;
    }
    /**
     * @param phone 要设置的 phone。
     */
    public final void setPhone(String phone)
    {
        this.phone = phone;
    }
    /**
     * @return 返回 roles。
     */
    public final Set getRoles()
    {
        return roles;
    }
    /**
     * @param roles 要设置的 roles。
     */
    public final void setRoles(Set roles)
    {
        this.roles = roles;
    }
    /**
     * @return 返回 status。
     */
    public final boolean getStatus()
    {
        return status;
    }
    /**
     * @param status 要设置的 status。
     */
    public final void setStatus(boolean status)
    {
        this.status = status;
    }
    /**
     * @return 返回 caption。
     */
    public final String getCaption()
    {
        return caption;
    }
    /**
     * @param caption 要设置的 caption。
     */
    public final void setCaption(String title)
    {
        this.caption = title;
    }

    /**
     * @return
     */
    public String getCreationIp()
    {
        return baseInfo.getCreationIp();
    }
    /**
     * @return
     */
    public Timestamp getCreationTime()
    {
        return baseInfo.getCreationTime();
    }
    /**
     * @return
     */
    public String getCreator()
    {
        return baseInfo.getCreator();
    }
    /**
     * @return
     */
    public String getDescription()
    {
        return baseInfo.getDescription();
    }
    /**
     * @return
     */
    public Integer getId()
    {
        return baseInfo.getId();
    }
    /**
     * @return
     */
    public String getLastModifyIp()
    {
        return baseInfo.getLastModifyIp();
    }
    /**
     * @return
     */
    public Timestamp getLastModifyTime()
    {
        return baseInfo.getLastModifyTime();
    }
    /**
     * @return
     */
    public String getLoginName()
    {
        return baseInfo.getName();
    }
    /**
     * @param creationIp
     */
    public void setCreationIp(String creationIp)
    {
        baseInfo.setCreationIp(creationIp);
    }
    /**
     * @param creationTime
     */
    public void setCreationTime(Timestamp creationTime)
    {
        baseInfo.setCreationTime(creationTime);
    }
    /**
     * @param creator
     */
    public void setCreator(String creator)
    {
        baseInfo.setCreator(creator);
    }
    /**
     * @param description
     */
    public void setDescription(String description)
    {
        baseInfo.setDescription(description);
    }
    /**
     * @param id
     */
    public void setId(Integer id)
    {
        baseInfo.setId(id);
    }
    /**
     * @param lastModifyIp
     */
    public void setLastModifyIp(String lastModifyIp)
    {
        baseInfo.setLastModifyIp(lastModifyIp);
    }
    /**
     * @param lastModifyTime
     */
    public void setLastModifyTime(Timestamp lastModifyTime)
    {
        baseInfo.setLastModifyTime(lastModifyTime);
    }
    /**
     * @param name
     */
    public void setLoginName(String name)
    {
        baseInfo.setName(name);
    }
    /**
     * @return 返回 lastLoginIp。
     */
    public final String getLastLoginIp()
    {
        return lastLoginIp;
    }
    /**
     * @param lastLoginIp 要设置的 lastLoginIp。
     */
    public final void setLastLoginIp(String lastLoginIp)
    {
        this.lastLoginIp = lastLoginIp;
    }
    /**
     * @return 返回 userName。
     */
    public final String getUserName()
    {
        return userName;
    }
    /**
     * @param userName 要设置的 userName。
     */
    public final void setUserName(String userName)
    {
        this.userName = userName;
    }
    /**
     * @param request
     */
    public void setDefaultOnAdd(HttpServletRequest request)
    {
        baseInfo.setDefaultOnAdd(request);
    }
    /**
     * @param request
     */
    public void setDefaultOnUpdate(HttpServletRequest request)
    {
        baseInfo.setDefaultOnUpdate(request);
    }
    /**
     * @return 返回 email。
     */
    public final String getEmail()
    {
        return email;
    }
    /**
     * @param email 要设置的 email。
     */
    public final void setEmail(String email)
    {
        this.email = email;
    }

    private String oldPassword;
    private String password;
    private String type;
	
	private String userName;
	private String caption;
	private String phone;
	private String department;
	private String fax;
	private String email;
	
	private boolean status;
	private Integer failedLoginTimes;

	private Date lastModifyPasswordTime;
	private Date beginTime;
	
	private Integer liveTime; // inactiveSessionTime
	private Date expireTime;
	private Date lastLoginTime;
	
	private String lastLoginIp;
	private String minIp;
	private String maxIp;

	private Set roles;

	private String roleId;//角色ID sm版使用
    private String roleName;//角色名称 sm版使用
	private BaseInfo baseInfo;

	/*最大同时在线数*/
    private Integer maxOnline;
    /*允许登录IP（多个） 支持IPv4段、IPv6格式*/
    private String allowIpsStr;
    private Set allowIps;
    /**
     * 用户信息 excel
     */

    public Integer getMaxOnline() {
        return maxOnline;
    }

    public void setMaxOnline(Integer maxOnline) {
        this.maxOnline = maxOnline;
    }

    public Set getAllowIps() {
        if(StringUtils.isEmpty(this.allowIpsStr)) {
            return this.allowIps;
        }
        Set allowIps = allowIpsStrToAllowIps(this.allowIpsStr);
        this.setAllowIps(allowIps);
        return allowIps;
    }

    public void setAllowIps(Set allowIps) {
        this.allowIps = allowIps;
    }

    public String getAllowIpsStr() {
        return allowIpsStr;
    }

    public void setAllowIpsStr(String allowIpsStr) {
        this.allowIpsStr = allowIpsStr;
    }

    public Set allowIpsStrToAllowIps(String ipsStr){
        Set allowIps = new HashSet();
        for(String ip : ipsStr.split(",")){
            allowIps.add(ip);
        }
        return allowIps;
    }

    /**
     * @return
     */
    public boolean isCreate()
    {
        return baseInfo.isCreate();
    }
    /**
     * @return
     */
    public boolean isDelete()
    {
        return baseInfo.isDelete();
    }
    /**
     * @return
     */
    public boolean isExecute()
    {
        return baseInfo.isExecute();
    }
    /**
     * @return
     */
    public boolean isRead()
    {
        return baseInfo.isRead();
    }
    /**
     * @return
     */
    public boolean isWrite()
    {
        return baseInfo.isWrite();
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }




}
