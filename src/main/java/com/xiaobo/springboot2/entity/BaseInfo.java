/*
 * 创建日期 2005-9-7
 *
 * TODO 要更改此生成的文件的模板，请转至
 * 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */
package com.xiaobo.springboot2.entity;


import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Timestamp;

/**
 * @author lei_peng
 *
 * TODO 要更改此生成的类型注释的模板，请转至
 * 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */
public class BaseInfo implements Serializable
{
	private String name;
	private String description;
	private Integer id;

	private String creator;
	private String creationIp;
	private Timestamp creationTime;

    private String lastModifyIp;
    private String lastModifyUser;
    private Timestamp lastModifyTime;
    
    /**
     * @return 返回 create。
     */
    public final boolean isCreate()
    {
        return create;
    }
    /**
     * @return 返回 delete。
     */
    public final boolean isDelete()
    {
        return delete;
    }
    /**
     * @return 返回 execute。
     */
    public final boolean isExecute()
    {
        return execute;
    }
    /**
     * @return 返回 read。
     */
    public final boolean isRead()
    {
        return read;
    }
    /**
     * @return 返回 write。
     */
    public final boolean isWrite()
    {
        return write;
    }
    private boolean create;
    private boolean read;
    private boolean write;
    private boolean execute;
    private boolean delete;
    private Integer actions;

    public static String toIpString(BigInteger ipNumber) throws UnknownHostException
    {
        if (ipNumber == null) return null;   
        return ""+ipNumber;
        //return InetAddress.getByAddress(ipNumber.toByteArray()).getHostAddress();
    }
    public static BigInteger toIpNumber(String ipString) throws UnknownHostException
    {
        if (ipString == null) return null;
        return new BigInteger(InetAddress.getByName(ipString).getAddress());
    }
    
    public void setDefaultOnAdd(final HttpServletRequest request)
    {
        SessionFacade authSession =
            (SessionFacade)request.getSession().getAttribute("authSession");
        this.setCreator(authSession.getLoginUser());
        this.setCreationIp(request.getRemoteHost());
        this.setCreationTime(new Timestamp(System.currentTimeMillis()));
        
        setDefaultOnUpdate(request);
    }
    public void setDefaultOnUpdate(final HttpServletRequest request)
    {
        SessionFacade authSession =
            (SessionFacade)request.getSession().getAttribute("authSession");
        this.setLastModifyIp(request.getRemoteHost());
        this.setLastModifyTime(new Timestamp(System.currentTimeMillis()));
        this.setLastModifyUser(authSession.getLoginUser());
    }
    public void reset()
    {
//        id = null;
        name = "";
        description = "";
        
        creator = "";
    	creationIp = null;
        creationTime = null;

        lastModifyIp = null;
        lastModifyTime = null;
        lastModifyUser = "";
    }
    
    /**
     * @return 返回 creationIp。
     */
    public final String getCreationIp()
    {
        return creationIp;
    }
    /**
     * @param creationIp 要设置的 creationIp。
     */
    public final void setCreationIp(String creationIp)
    {
        this.creationIp = creationIp;
    }
    /**
     * @return 返回 creationTime。
     */
    public final Timestamp getCreationTime()
    {
        return creationTime;
    }
    /**
     * @param creationTime 要设置的 creationTime。
     */
    public final void setCreationTime(Timestamp creationTime)
    {
        this.creationTime = creationTime;
    }
    /**
     * @return 返回 creator。
     */
    public final String getCreator()
    {
        return creator;
    }
    /**
     * @param creator 要设置的 creator。
     */
    public final void setCreator(String creator)
    {
        this.creator = creator;
    }
    /**
     * @return 返回 description。
     */
    public final String getDescription()
    {
        return description;
    }
    /**
     * @param description 要设置的 description。
     */
    public final void setDescription(String description)
    {
        this.description = description;
    }
    /**
     * @return 返回 id。
     */
    public final Integer getId()
    {
        return id;
    }
    /**
     * @param id 要设置的 id。
     */
    public final void setId(Integer id)
    {
        this.id = id;
    }
    /**
     * @return 返回 lastModifyIp。
     */
    public final String getLastModifyIp()
    {
        return lastModifyIp;
    }
    /**
     * @param lastModifyIp 要设置的 lastModifyIp。
     */
    public final void setLastModifyIp(String lastModifyIp)
    {
        this.lastModifyIp = lastModifyIp;
    }
    /**
     * @return 返回 lastModifyTime。
     */
    public final Timestamp getLastModifyTime()
    {
        return lastModifyTime;
    }
    /**
     * @param lastModifyTime 要设置的 lastModifyTime。
     */
    public final void setLastModifyTime(Timestamp lastModifyTime)
    {
        this.lastModifyTime = lastModifyTime;
    }
    /**
     * @return 返回 name。
     */
    public final String getName()
    {
        return name;
    }
    /**
     * @param name 要设置的 name。
     */
    public final void setName(String name)
    {
        this.name = name;
    }
    /**
     * @return 返回 lastModifyUser。
     */
    public final String getLastModifyUser()
    {
        return lastModifyUser;
    }
    /**
     * @param lastModifyUser 要设置的 lastModifyUser。
     */
    public final void setLastModifyUser(String lastModifyUser)
    {
        this.lastModifyUser = lastModifyUser;
    }
    /**
     * @param create 要设置的 create。
     */
    public final void setCreate(boolean create)
    {
        this.create = create;
    }
    /**
     * @param delete 要设置的 delete。
     */
    public final void setDelete(boolean delete)
    {
        this.delete = delete;
    }
    /**
     * @param execute 要设置的 execute。
     */
    public final void setExecute(boolean execute)
    {
        this.execute = execute;
    }
    /**
     * @param read 要设置的 read。
     */
    public final void setRead(boolean read)
    {
        this.read = read;
    }
    /**
     * @param write 要设置的 write。
     */
    public final void setWrite(boolean write)
    {
        this.write = write;
    }

    public Integer getActions() {
        return actions;
    }

    public void setActions(Integer actions) {
        this.actions = actions;
    }
}
