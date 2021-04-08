/*
 * �������� 2005-9-7
 *
 * TODO Ҫ���Ĵ����ɵ��ļ���ģ�壬��ת��
 * ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
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
 * TODO Ҫ���Ĵ����ɵ�����ע�͵�ģ�壬��ת��
 * ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
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
     * @return ���� create��
     */
    public final boolean isCreate()
    {
        return create;
    }
    /**
     * @return ���� delete��
     */
    public final boolean isDelete()
    {
        return delete;
    }
    /**
     * @return ���� execute��
     */
    public final boolean isExecute()
    {
        return execute;
    }
    /**
     * @return ���� read��
     */
    public final boolean isRead()
    {
        return read;
    }
    /**
     * @return ���� write��
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
     * @return ���� creationIp��
     */
    public final String getCreationIp()
    {
        return creationIp;
    }
    /**
     * @param creationIp Ҫ���õ� creationIp��
     */
    public final void setCreationIp(String creationIp)
    {
        this.creationIp = creationIp;
    }
    /**
     * @return ���� creationTime��
     */
    public final Timestamp getCreationTime()
    {
        return creationTime;
    }
    /**
     * @param creationTime Ҫ���õ� creationTime��
     */
    public final void setCreationTime(Timestamp creationTime)
    {
        this.creationTime = creationTime;
    }
    /**
     * @return ���� creator��
     */
    public final String getCreator()
    {
        return creator;
    }
    /**
     * @param creator Ҫ���õ� creator��
     */
    public final void setCreator(String creator)
    {
        this.creator = creator;
    }
    /**
     * @return ���� description��
     */
    public final String getDescription()
    {
        return description;
    }
    /**
     * @param description Ҫ���õ� description��
     */
    public final void setDescription(String description)
    {
        this.description = description;
    }
    /**
     * @return ���� id��
     */
    public final Integer getId()
    {
        return id;
    }
    /**
     * @param id Ҫ���õ� id��
     */
    public final void setId(Integer id)
    {
        this.id = id;
    }
    /**
     * @return ���� lastModifyIp��
     */
    public final String getLastModifyIp()
    {
        return lastModifyIp;
    }
    /**
     * @param lastModifyIp Ҫ���õ� lastModifyIp��
     */
    public final void setLastModifyIp(String lastModifyIp)
    {
        this.lastModifyIp = lastModifyIp;
    }
    /**
     * @return ���� lastModifyTime��
     */
    public final Timestamp getLastModifyTime()
    {
        return lastModifyTime;
    }
    /**
     * @param lastModifyTime Ҫ���õ� lastModifyTime��
     */
    public final void setLastModifyTime(Timestamp lastModifyTime)
    {
        this.lastModifyTime = lastModifyTime;
    }
    /**
     * @return ���� name��
     */
    public final String getName()
    {
        return name;
    }
    /**
     * @param name Ҫ���õ� name��
     */
    public final void setName(String name)
    {
        this.name = name;
    }
    /**
     * @return ���� lastModifyUser��
     */
    public final String getLastModifyUser()
    {
        return lastModifyUser;
    }
    /**
     * @param lastModifyUser Ҫ���õ� lastModifyUser��
     */
    public final void setLastModifyUser(String lastModifyUser)
    {
        this.lastModifyUser = lastModifyUser;
    }
    /**
     * @param create Ҫ���õ� create��
     */
    public final void setCreate(boolean create)
    {
        this.create = create;
    }
    /**
     * @param delete Ҫ���õ� delete��
     */
    public final void setDelete(boolean delete)
    {
        this.delete = delete;
    }
    /**
     * @param execute Ҫ���õ� execute��
     */
    public final void setExecute(boolean execute)
    {
        this.execute = execute;
    }
    /**
     * @param read Ҫ���õ� read��
     */
    public final void setRead(boolean read)
    {
        this.read = read;
    }
    /**
     * @param write Ҫ���õ� write��
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
