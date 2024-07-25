package com.pearson.pix.presentation.admin.command;

import com.pearson.pix.business.common.PIXUtil;
import com.pearson.pix.dto.admin.*;
import com.pearson.pix.dto.common.Country;
import com.pearson.pix.dto.common.Reference;
import com.pearson.pix.exception.AppException;
import com.pearson.pix.presentation.admin.action.*;
import com.pearson.pix.presentation.admin.delegate.AdminDelegate;
import com.pearson.pix.presentation.base.command.BaseCommand;
import java.util.*;
import javax.servlet.http.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.*;

public class AdminCommand extends BaseCommand
{

    int flag;
    int flagForPublisher;
    private static Log log;

    public AdminCommand()
    {
        flag = 0;
        flagForPublisher = 0;
    }

    public String executeInsert(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, ActionMessages messages)
    {
        String mapForward = null;
        String adminModule = request.getParameter("ADMIN_MODULE");
        if("PUB".equals(adminModule))
        {
            mapForward = executePubUnitInsert(mapping, form, request, response);
        } else
        if("SUP".equals(adminModule))
        {
            mapForward = executeSupplierInsert(mapping, form, request, response);
        } else
        if("USER".equals(adminModule))
        {
            mapForward = executeUserInsert(mapping, form, request, response);
        }
        return mapForward;
    }

    public String executeList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, ActionMessages messages)
    {
        String mapForward = null;
        String adminModule = request.getParameter("ADMIN_MODULE");
        if("PUB".equals(adminModule))
        {
            mapForward = executePubUnitsList(mapping, form, request, response);
        } else
        if("SUP".equals(adminModule))
        {
            mapForward = executeSuppliersList(mapping, form, request, response);
        } else
        if("USER".equals(adminModule))
        {
            mapForward = executeUsersList(mapping, form, request, response);
        }
        return mapForward;
    }

    public String executeRelatedList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, ActionMessages messages)
    {
        String mapForward = null;
        mapForward = executeUserRelatedList(mapping, form, request, response);
        return mapForward;
    }

    public String executeUpdate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, ActionMessages messages)
    {
        String mapForward = null;
        String adminModule = request.getParameter("ADMIN_MODULE");
        if("PUB".equals(adminModule))
        {
            mapForward = executePubUnitUpdate(mapping, form, request, response);
        } else
        if("SUP".equals(adminModule))
        {
            mapForward = executeSupplierUpdate(mapping, form, request, response);
        } else
        if("USER".equals(adminModule))
        {
            mapForward = executeUserUpdate(mapping, form, request, response);
        }
        return mapForward;
    }

    public String executeDisplay(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, ActionMessages messages)
    {
        String adminModule = null;
        if(request.getSession().getAttribute("ADMIN_MODULE") != null)
        {
            adminModule = (String)request.getSession().getAttribute("ADMIN_MODULE");
        } else
        {
            adminModule = request.getParameter("ADMIN_MODULE");
        }
        if("PUB".equals(adminModule))
        {
            executePubUnitRelatedList(mapping, form, request, response);
            String san = request.getParameter("SAN_VALUE");
            if(san != null)
            {
                String mapForward = null;
                mapForward = executePubUnitDisplay(mapping, form, request, response);
                return mapForward;
            }
        } else
        if("SUP".equals(adminModule))
        {
            executeSupplierRelatedList(mapping, form, request, response);
            String san = request.getParameter("SAN_VALUE_SUPPLIER");
            if(san != null)
            {
                String mapForward = null;
                mapForward = executeSupplierDisplay(mapping, form, request, response);
                return mapForward;
            }
        } else
        if("USER".equals(adminModule))
        {
            String login = null;
            executeUserRelatedList(mapping, form, request, response);
            if(request.getSession().getAttribute("LOGIN") != null)
            {
                login = (String)request.getSession().getAttribute("LOGIN");
            } else
            {
                login = request.getParameter("LOGIN");
            }
            if(login != null)
            {
            	request.setAttribute("tokenTab", request.getAttribute("tokenTab"));
                String mapForward = null;
//                mapForward = executeUserDisplay(mapping, form, request, response);
                mapForward = executeModifiedUserDisplay(mapping, form, request, response);
                return mapForward;
            }
        }
        return "display";
    }

    public String executeDelete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, ActionMessages messages)
    {
        String mapForward = null;
        String adminModule = request.getParameter("ADMIN_MODULE");
        if("SUP".equals(adminModule))
        {
            mapForward = executeSupplierDelete(mapping, form, request, response);
        } else
        if("PUB".equals(adminModule))
        {
            mapForward = executePublisherDelete(mapping, form, request, response);
        } else
        if("USER".equals(adminModule))
        {
            mapForward = executeUserDelete(mapping, form, request, response);
        }
        return mapForward;
    }

    private String executePubUnitInsert(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        AddPubUnitForm pubUnitForm = (AddPubUnitForm)form;
        AdminDelegate objAdminDelegate = new AdminDelegate();
        Party adminParty = pubUnitForm.getParty();
        Country objCountry = new Country();
        PartyTransport objPartyTransport = null;
        User objUser = null;
        Integer userId = null;
        try
        {
            Calendar cal = Calendar.getInstance();
            java.util.Date today = cal.getTime();
            if(request.getParameter("PAGE_VALUE") != "" && request.getParameter("PAGE_VALUE") != null)
            {
                String page_value = request.getParameter("PAGE_VALUE");
                String list_page = "?PAGE_VALUE=" + page_value;
                if((!PIXUtil.checkNullField(request.getParameter("sanFilter"))) & (!PIXUtil.checkNullField(request.getParameter("nameFilter"))) & (!PIXUtil.checkNullField(request.getParameter("statusFilter"))) & (!PIXUtil.checkNullField(request.getParameter("startDateFilter"))) & (!PIXUtil.checkNullField(request.getParameter("endDateFilter"))))
                {
                    request.setAttribute("OK_URL", "/admin/listpub.do?PAGE_VALUE=" + page_value + "&ADMIN_MODULE=PUB");
                } else
                {
                    if(request.getParameter("sanFilter") != "")
                    {
                        list_page = list_page + "&sanFilter=" + request.getParameter("sanFilter");
                    }
                    if(request.getParameter("nameFilter") != "")
                    {
                        list_page = list_page + "&nameFilter=" + request.getParameter("nameFilter");
                    }
                    if(request.getParameter("statusFilter") != "")
                    {
                        list_page = list_page + "&statusFilter=" + request.getParameter("statusFilter");
                    }
                    if(request.getParameter("startDateFilter") != "")
                    {
                        list_page = list_page + "&startDateFilter=" + request.getParameter("startDateFilter");
                    }
                    if(request.getParameter("endDateFilter") != "")
                    {
                        list_page = list_page + "&endDateFilter=" + request.getParameter("endDateFilter");
                    }
                    request.setAttribute("OK_URL", "/admin/listpub.do" + list_page + "&ADMIN_MODULE=PUB");
                }
            }
            if(request.getSession().getAttribute("USER_INFO") != null)
            {
                objUser = (User)request.getSession().getAttribute("USER_INFO");
                userId = objUser.getUserId();
            }
            adminParty.setPartyType("B");
            if(adminParty.getSan() != null)
            {
                adminParty.setSan(adminParty.getSan().trim());
            }
            if(adminParty.getName1() != null)
            {
                adminParty.setName1(adminParty.getName1().trim());
            }
            if(adminParty.getContactFirstName() != null)
            {
                adminParty.setContactFirstName(adminParty.getContactFirstName().trim());
            }
            if(adminParty.getContactLastName() != null)
            {
                adminParty.setContactLastName(adminParty.getContactLastName().trim());
            }
            if(adminParty.getPhone1() != null)
            {
                adminParty.setPhone1(adminParty.getPhone1().trim());
            }
            if(adminParty.getPhone2() != null)
            {
                adminParty.setPhone2(adminParty.getPhone2().trim());
            }
            if(adminParty.getFax1() != null)
            {
                adminParty.setFax1(adminParty.getFax1().trim());
            }
            if(adminParty.getFax2() != null)
            {
                adminParty.setFax2(adminParty.getFax2().trim());
            }
            if(adminParty.getMobile() != null)
            {
                adminParty.setMobile(adminParty.getMobile().trim());
            }
            if(adminParty.getAddress1() != null)
            {
                adminParty.setAddress1(adminParty.getAddress1().trim());
            }
            if(adminParty.getAddress2() != null)
            {
                adminParty.setAddress2(adminParty.getAddress2().trim());
            }
            if(adminParty.getAddress3() != null)
            {
                adminParty.setAddress3(adminParty.getAddress3().trim());
            }
            if(adminParty.getAddress4() != null)
            {
                adminParty.setAddress4(adminParty.getAddress4().trim());
            }
            if(adminParty.getState() != null)
            {
                adminParty.setState(adminParty.getState().trim());
            }
            if(adminParty.getCity() != null)
            {
                adminParty.setCity(adminParty.getCity().trim());
            }
            if(adminParty.getPostalCode() != null)
            {
                adminParty.setPostalCode(adminParty.getPostalCode().trim());
            }
            if(adminParty.getEmail() != null)
            {
                adminParty.setEmail(adminParty.getEmail().trim());
            }
            if(adminParty.getWebsite() != null)
            {
                adminParty.setWebsite(adminParty.getWebsite().trim());
            }
            objCountry.setCountryCode(adminParty.getCountryDetail().getCountryCode());
            adminParty.setCountryDetail(objCountry);
            adminParty.setTransportDetail(objPartyTransport);
            adminParty.setCreationDateTime(today);
            adminParty.setModDateTime(today);
            adminParty.setCreatedBy(userId);
            adminParty.setModifiedBy(userId);
            String san_no = objAdminDelegate.savePartyDetail(adminParty);
            String messageKey = "San No. " + san_no + " has been successfully saved.";
            request.setAttribute("SUCCESS_STRING", messageKey);
        }
        catch(AppException e)
        {
            String errMsg = e.getSErrorDescription();
            request.setAttribute("PIX_ERROR", errMsg);
            return "error";
        }
        catch(Exception e)
        {
            AppException ae = new AppException();
            ae.performErrorAction("9000", "AdminCommand,executePubUnitInsert", e);
            String errMsg = ae.getSErrorDescription();
            request.setAttribute("PIX_ERROR", errMsg);
            return "error";
        }
        return "insert";
    }

    private String executePubUnitsList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        String objOrderBy = "CREATION_DATE_TIME";
        String objSort = "DESC";
        String startDate = null;
        String endDate = null;
        String startDateFilter = null;
        String endDateFilter = null;
        Party adminParty = null;
        AdminDelegate objAdminDelegate = null;
        try
        {
            request.setAttribute("PATH", request.getContextPath());
            if(request.getSession().getAttribute("pubList") != null)
            {
                request.getSession().removeAttribute("pubList");
            }
            if(request.getParameter("FLAGPUB") != "" && request.getParameter("FLAGPUB") != null)
            {
            	flagForPublisher = "".equals(request.getParameter("FLAGPUB"))?0:Integer.parseInt(request.getParameter("FLAGPUB"));//flagForPublisher = Integer.parseInt(request.getParameter("FLAGPUB"));
                flag = 0;System.out.println("VCNANIT request.getParameter(\"FLAGPUB\")"+request.getParameter("FLAGPUB"));
            }
            if(request.getParameter("FLAGPUB") == "" && request.getParameter("FLAGSUP") == null && "USER_EDIT".equals(request.getParameter("METHOD")))
            {
                flag = 0;
                flagForPublisher = 0;
            }
            if(flagForPublisher == 0 || (AddPubUnitForm)request.getSession().getAttribute("addPubUnitForm") == null || request.getParameter("FLAGPUB") != "")
            {
                AddPubUnitForm addPubUnitForm = (AddPubUnitForm)form;
                adminParty = new Party();
                if("PubEditCancel".equals(request.getParameter("PubEditCancel")))
                {
                    if(!PIXUtil.checkNullField(request.getParameter("sanFilter")))
                    {
                        adminParty.setSan(null);
                    } else
                    {
                        request.setAttribute("sanFilter", request.getParameter("sanFilter"));
                        String sanFilter = request.getParameter("sanFilter");
                        adminParty.setSan(sanFilter);
                    }
                    if(!PIXUtil.checkNullField(request.getParameter("nameFilter")))
                    {
                        adminParty.setName1(null);
                    } else
                    {
                        request.setAttribute("nameFilter", request.getParameter("nameFilter"));
                        String nameFilter = request.getParameter("nameFilter");
                        adminParty.setName1(nameFilter);
                    }
                    if(!PIXUtil.checkNullField(request.getParameter("statusFilter")))
                    {
                        adminParty.setActiveFlag(null);
                    } else
                    {
                        request.setAttribute("statusFilter", request.getParameter("statusFilter"));
                        String statusFilter = request.getParameter("statusFilter");
                        adminParty.setActiveFlag(statusFilter);
                    }
                    adminParty.setPartyType("B");
                    if(PIXUtil.checkNullField(request.getParameter("startDateFilter")))
                    {
                        request.setAttribute("startDateFilter", request.getParameter("startDateFilter"));
                        startDateFilter = request.getParameter("startDateFilter");
                    }
                    if(PIXUtil.checkNullField(request.getParameter("endDateFilter")))
                    {
                        request.setAttribute("endDateFilter", request.getParameter("endDateFilter"));
                        endDateFilter = request.getParameter("endDateFilter");
                    }
                } else
                {
                    adminParty.setSan(addPubUnitForm.getParty().getSan());
                    adminParty.setName1(addPubUnitForm.getParty().getName1());
                    adminParty.setActiveFlag(addPubUnitForm.getParty().getActiveFlag());
                    adminParty.setPartyType("B");
                    startDate = addPubUnitForm.getStartDate();
                    endDate = addPubUnitForm.getEndDate();
                    String statusFilter = request.getParameter("party.activeFlag");
                    String sanFilter = request.getParameter("party.san");
                    String nameFilter = request.getParameter("party.name1");
                    startDateFilter = addPubUnitForm.getStartDate();
                    endDateFilter = addPubUnitForm.getEndDate();
                    if(PIXUtil.checkNullField(statusFilter))
                    {
                        request.setAttribute("statusFilter", statusFilter);
                    } else
                    if(PIXUtil.checkNullField(request.getParameter("statusFilter")))
                    {
                        request.setAttribute("statusFilter", request.getParameter("statusFilter"));
                        statusFilter = request.getParameter("statusFilter");
                        adminParty.setActiveFlag(statusFilter);
                    }
                    if(PIXUtil.checkNullField(sanFilter))
                    {
                        request.setAttribute("sanFilter", sanFilter);
                    } else
                    if(PIXUtil.checkNullField(request.getParameter("sanFilter")))
                    {
                        request.setAttribute("sanFilter", request.getParameter("sanFilter"));
                        sanFilter = request.getParameter("sanFilter");
                        adminParty.setSan(sanFilter);
                    }
                    if(PIXUtil.checkNullField(nameFilter))
                    {
                        request.setAttribute("nameFilter", nameFilter);
                    } else
                    if(PIXUtil.checkNullField(request.getParameter("nameFilter")))
                    {
                        request.setAttribute("nameFilter", request.getParameter("nameFilter"));
                        nameFilter = request.getParameter("nameFilter");
                        adminParty.setName1(nameFilter);
                    }
                    if(PIXUtil.checkNullField(startDateFilter))
                    {
                        request.setAttribute("startDateFilter", startDateFilter);
                    } else
                    if(PIXUtil.checkNullField(request.getParameter("startDateFilter")))
                    {
                        request.setAttribute("startDateFilter", request.getParameter("startDateFilter"));
                        startDateFilter = request.getParameter("startDateFilter");
                    }
                    if(PIXUtil.checkNullField(endDateFilter))
                    {
                        request.setAttribute("endDateFilter", endDateFilter);
                    } else
                    if(PIXUtil.checkNullField(request.getParameter("endDateFilter")))
                    {
                        request.setAttribute("endDateFilter", request.getParameter("endDateFilter"));
                        endDateFilter = request.getParameter("endDateFilter");
                    }
                }
                objAdminDelegate = new AdminDelegate();
                Vector objPubUnitList;
                if("partyListPub".equals(request.getParameter("partyListPub")))
                {
                    objPubUnitList = objAdminDelegate.displayPartiesList(adminParty, startDate, endDate, 0, objOrderBy, objSort);
                } else
                {
                    startDate = startDateFilter;
                    endDate = endDateFilter;
                    int currentValue = Integer.parseInt(request.getParameter("PAGE_VALUE"));
                    objPubUnitList = objAdminDelegate.displayPartiesList(adminParty, startDate, endDate, currentValue, objOrderBy, objSort);
                    int size = objPubUnitList != null ? objPubUnitList.size() : 0;
                    PIXUtil.getNextPage(request, currentValue, size);
                    PIXUtil.getPrevPage(request, currentValue);
                    if(size > 10)
                    {
                        objPubUnitList.remove(objPubUnitList.get(size - 1));
                    }
                }
                if("USER_EDIT".equals(request.getParameter("METHOD")))
                {
                    UserForm objUserForm = (UserForm)request.getSession().getAttribute("userForm");
                    Vector objPartyCollection = (Vector)objUserForm.getUser().getPartyCollection();
                    int partyCollectionSize = objPartyCollection != null ? objPartyCollection.size() : 0;
                    for(int j = 0; j < partyCollectionSize; j++)
                    {
                        Party newParty = (Party)objPartyCollection.elementAt(j);
                        for(int i = 0; i < objPubUnitList.size(); i++)
                        {
                            Party publisherParty = (Party)objPubUnitList.get(i);
                            if(newParty.getSan().equals(publisherParty.getSan()))
                            {
                                objPubUnitList.remove(publisherParty);
                            }
                        }

                    }

                    addPubUnitForm.setPubUnitListCollection(objPubUnitList);
                }
                addPubUnitForm.setPubUnitListCollection(objPubUnitList);
                if((AddPubUnitForm)request.getSession().getAttribute("addPubUnitForm") != null)
                {
                    flagForPublisher = 1;
                }
            } else
            {
                AddPubUnitForm objPublisherForm = (AddPubUnitForm)request.getSession().getAttribute("addPubUnitForm");
                Vector objPubUnitList = objPublisherForm.getPubUnitListCollection();
                objPublisherForm.setPubUnitListCollection(objPubUnitList);
            }
            return "list";
        }
        catch(AppException e)
        {
            String errMsg = e.getSErrorDescription();
            request.setAttribute("PIX_ERROR", errMsg);
            return "error";
        }
        catch(Exception e)
        {
            AppException ae = new AppException();
            ae.performErrorAction("9000", "AdminCommand,executePubUnitsList", e);
            String errMsg = ae.getSErrorDescription();
            request.setAttribute("PIX_ERROR", errMsg);
            return "error";
        }
    }

    private String executePubUnitUpdate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        AddPubUnitForm addPubUnitForm = (AddPubUnitForm)form;
        AdminDelegate objAdminDelegate = new AdminDelegate();
        Party adminParty = addPubUnitForm.getParty();
        User objUser = null;
        Integer userId = null;
        try
        {
            Calendar cal = Calendar.getInstance();
            java.util.Date today = cal.getTime();
            if(request.getParameter("PAGE_VALUE") != "" && request.getParameter("PAGE_VALUE") != null)
            {
                String page_value = request.getParameter("PAGE_VALUE");
                String list_page = "?PAGE_VALUE=" + page_value;
                if((!PIXUtil.checkNullField(request.getParameter("sanFilter"))) & (!PIXUtil.checkNullField(request.getParameter("nameFilter"))) & (!PIXUtil.checkNullField(request.getParameter("statusFilter"))) & (!PIXUtil.checkNullField(request.getParameter("startDateFilter"))) & (!PIXUtil.checkNullField(request.getParameter("endDateFilter"))))
                {
                    request.setAttribute("OK_URL", "/admin/listpub.do?PAGE_VALUE=" + page_value + "&ADMIN_MODULE=PUB");
                } else
                {
                    if(request.getParameter("sanFilter") != "")
                    {
                        list_page = list_page + "&sanFilter=" + request.getParameter("sanFilter");
                    }
                    if(request.getParameter("nameFilter") != "")
                    {
                        list_page = list_page + "&nameFilter=" + request.getParameter("nameFilter");
                    }
                    if(request.getParameter("statusFilter") != "")
                    {
                        list_page = list_page + "&statusFilter=" + request.getParameter("statusFilter");
                    }
                    if(request.getParameter("startDateFilter") != "")
                    {
                        list_page = list_page + "&startDateFilter=" + request.getParameter("startDateFilter");
                    }
                    if(request.getParameter("endDateFilter") != "")
                    {
                        list_page = list_page + "&endDateFilter=" + request.getParameter("endDateFilter");
                    }
                    request.setAttribute("OK_URL", "/admin/listpub.do" + list_page + "&ADMIN_MODULE=PUB");
                }
            } else
            {
                request.setAttribute("OK_URL", "/admin/listpub.do?PAGE_VALUE=1&ADMIN_MODULE=PUB");
            }
            if(request.getSession().getAttribute("USER_INFO") != null)
            {
                objUser = (User)request.getSession().getAttribute("USER_INFO");
                userId = objUser.getUserId();
            }
            adminParty.setPartyType("B");
            adminParty.setModDateTime(today);
            adminParty.setModifiedBy(userId);
            String san_no = objAdminDelegate.updatePartyDetail(adminParty);
            String messageKey = "San No. " + san_no + " has been successfully updated.";
            request.setAttribute("SUCCESS_STRING", messageKey);
        }
        catch(AppException e)
        {
            String errMsg = e.getSErrorDescription();
            request.setAttribute("PIX_ERROR", errMsg);
            return "error";
        }
        catch(Exception e)
        {
            AppException ae = new AppException();
            ae.performErrorAction("9000", "AdminCommand,executePubUnitUpdate", e);
            String errMsg = ae.getSErrorDescription();
            request.setAttribute("PIX_ERROR", errMsg);
            return "error";
        }
        return "update";
    }

    private void executePubUnitRelatedList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        AdminDelegate objAdminDelegate = null;
        HashMap objHashMap = null;
        try
        {
            objAdminDelegate = new AdminDelegate();
            objHashMap = new HashMap();
            objHashMap = objAdminDelegate.getBasicPartyInfo();
            HttpSession session = request.getSession();
            session.setAttribute("pubList", objHashMap.get("COUNTRY_LIST"));
        }
        catch(AppException e)
        {
            String errMsg = e.getSErrorDescription();
            request.setAttribute("PIX_ERROR", errMsg);
        }
    }

    private String executePubUnitDisplay(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        AddPubUnitForm pubUnitForm = (AddPubUnitForm)form;
        AdminDelegate objAdminDelegate = new AdminDelegate();
        Party party = null;
        String san = request.getParameter("SAN_VALUE");
        try
        {
            party = objAdminDelegate.displayPartyDetail(san);
            pubUnitForm.setParty(party);
            return "display";
        }
        catch(AppException e)
        {
            String errMsg = e.getSErrorDescription();
            request.setAttribute("PIX_ERROR", errMsg);
            return "error";
        }
        catch(Exception e)
        {
            AppException ae = new AppException();
            ae.performErrorAction("9000", "AdminCommand,executePubUnitDisplay", e);
            String errMsg = ae.getSErrorDescription();
            request.setAttribute("PIX_ERROR", errMsg);
            return "error";
        }
    }

    private String executeSuppliersList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        String objOrderBy = "CREATION_DATE_TIME";
        String objSort = "DESC";
        String startDate = null;
        String endDate = null;
        String startDateFilter = null;
        String endDateFilter = null;
        Party adminParty = null;
        AdminDelegate objAdminDelegate = null;
        try
        {
            request.setAttribute("PATH", request.getContextPath());
            if(request.getSession().getAttribute("supplierListAccess") != null)
            {
                request.getSession().removeAttribute("supplierListAccess");
            }
            if(request.getSession().getAttribute("supplierListCountry") != null)
            {
                request.getSession().removeAttribute("supplierListCountry");
            }
            if(request.getSession().getAttribute("supplierTypes") != null)
            {
                request.getSession().removeAttribute("supplierTypes");
            }
            if(request.getParameter("FLAGSUP") != "" && request.getParameter("FLAGSUP") != null)
            {
            	flag = "".equals(request.getParameter("FLAGSUP"))?0:Integer.parseInt(request.getParameter("FLAGSUP"));//flag = Integer.parseInt(request.getParameter("FLAGSUP"));
                flagForPublisher = 0;
            }
            if(request.getParameter("FLAGSUP") == "" && request.getParameter("FLAGPUB") == null && "USER_EDIT".equals(request.getParameter("METHOD")))
            {
                flag = 0;
                flagForPublisher = 0;
            }
            if(flag == 0 || (SupplierForm)request.getSession().getAttribute("supplierForm") == null || request.getParameter("FLAGSUP") != "")
            {
                SupplierForm objSupplierForm = (SupplierForm)form;
                adminParty = new Party();
                if("SupplierEditCancel".equals(request.getParameter("SupplierEditCancel")))
                {
                    if(!PIXUtil.checkNullField(request.getParameter("sanFilter")))
                    {
                        adminParty.setSan(null);
                    } else
                    {
                        request.setAttribute("sanFilter", request.getParameter("sanFilter"));
                        String sanFilter = request.getParameter("sanFilter");
                        adminParty.setSan(sanFilter);
                    }
                    if(!PIXUtil.checkNullField(request.getParameter("nameFilter")))
                    {
                        adminParty.setName1(null);
                    } else
                    {
                        request.setAttribute("nameFilter", request.getParameter("nameFilter"));
                        String nameFilter = request.getParameter("nameFilter");
                        adminParty.setName1(nameFilter);
                    }
                    if(!PIXUtil.checkNullField(request.getParameter("statusFilter")))
                    {
                        adminParty.setActiveFlag(null);
                    } else
                    {
                        request.setAttribute("statusFilter", request.getParameter("statusFilter"));
                        String statusFilter = request.getParameter("statusFilter");
                        adminParty.setActiveFlag(statusFilter);
                    }
                    adminParty.setPartyType("V");
                    if(PIXUtil.checkNullField(request.getParameter("startDateFilter")))
                    {
                        request.setAttribute("startDateFilter", request.getParameter("startDateFilter"));
                        startDateFilter = request.getParameter("startDateFilter");
                    }
                    if(PIXUtil.checkNullField(request.getParameter("endDateFilter")))
                    {
                        request.setAttribute("endDateFilter", request.getParameter("endDateFilter"));
                        endDateFilter = request.getParameter("endDateFilter");
                    }
                } else
                {
                    adminParty.setSan(objSupplierForm.getParty().getSan());
                    adminParty.setName1(objSupplierForm.getParty().getName1());
                    adminParty.setActiveFlag(objSupplierForm.getParty().getActiveFlag());
                    adminParty.setPartyType("V");
                    startDate = objSupplierForm.getStartDate();
                    endDate = objSupplierForm.getEndDate();
                    String statusFilter = request.getParameter("party.activeFlag");
                    String sanFilter = request.getParameter("party.san");
                    String nameFilter = request.getParameter("party.name1");
                    startDateFilter = objSupplierForm.getStartDate();
                    endDateFilter = objSupplierForm.getEndDate();
                    if(PIXUtil.checkNullField(statusFilter))
                    {
                        request.setAttribute("statusFilter", statusFilter);
                    } else
                    if(PIXUtil.checkNullField(request.getParameter("statusFilter")))
                    {
                        request.setAttribute("statusFilter", request.getParameter("statusFilter"));
                        statusFilter = request.getParameter("statusFilter");
                        adminParty.setActiveFlag(statusFilter);
                    }
                    if(PIXUtil.checkNullField(sanFilter))
                    {
                        request.setAttribute("sanFilter", sanFilter);
                    } else
                    if(PIXUtil.checkNullField(request.getParameter("sanFilter")))
                    {
                        request.setAttribute("sanFilter", request.getParameter("sanFilter"));
                        sanFilter = request.getParameter("sanFilter");
                        adminParty.setSan(sanFilter);
                    }
                    if(PIXUtil.checkNullField(nameFilter))
                    {
                        request.setAttribute("nameFilter", nameFilter);
                    } else
                    if(PIXUtil.checkNullField(request.getParameter("nameFilter")))
                    {
                        request.setAttribute("nameFilter", request.getParameter("nameFilter"));
                        nameFilter = request.getParameter("nameFilter");
                        adminParty.setName1(nameFilter);
                    }
                    if(PIXUtil.checkNullField(startDateFilter))
                    {
                        request.setAttribute("startDateFilter", startDateFilter);
                    } else
                    if(PIXUtil.checkNullField(request.getParameter("startDateFilter")))
                    {
                        request.setAttribute("startDateFilter", request.getParameter("startDateFilter"));
                        startDateFilter = request.getParameter("startDateFilter");
                    }
                    if(PIXUtil.checkNullField(endDateFilter))
                    {
                        request.setAttribute("endDateFilter", endDateFilter);
                    } else
                    if(PIXUtil.checkNullField(request.getParameter("endDateFilter")))
                    {
                        request.setAttribute("endDateFilter", request.getParameter("endDateFilter"));
                        endDateFilter = request.getParameter("endDateFilter");
                    }
                }
                objAdminDelegate = new AdminDelegate();
                Vector objSupplierList;
                if("partyList".equals(request.getParameter("partyList")))
                {
                    objSupplierList = objAdminDelegate.displayPartiesList(adminParty, startDate, endDate, 0, objOrderBy, objSort);
                } else
                {
                    startDate = startDateFilter;
                    endDate = endDateFilter;
                    int currentValue = Integer.parseInt(request.getParameter("PAGE_VALUE"));
                    objSupplierList = objAdminDelegate.displayPartiesList(adminParty, startDate, endDate, currentValue, objOrderBy, objSort);
                    int size = objSupplierList != null ? objSupplierList.size() : 0;
                    PIXUtil.getNextPage(request, currentValue, size);
                    PIXUtil.getPrevPage(request, currentValue);
                    if(size > 10)
                    {
                        objSupplierList.remove(objSupplierList.get(size - 1));
                    }
                }
                if("USER_EDIT".equals(request.getParameter("METHOD")))
                {
                    UserForm objUserForm = (UserForm)request.getSession().getAttribute("userForm");
                    Vector objPartyCollection = (Vector)objUserForm.getUser().getPartyCollection();
                    int partyCollectionSize = objPartyCollection != null ? objPartyCollection.size() : 0;
                    for(int j = 0; j < partyCollectionSize; j++)
                    {
                        Party newParty = (Party)objPartyCollection.elementAt(j);
                        for(int i = 0; i < objSupplierList.size(); i++)
                        {
                            Party supplierParty = (Party)objSupplierList.get(i);
                            if(newParty.getSan().equals(supplierParty.getSan()))
                            {
                                objSupplierList.remove(supplierParty);
                            }
                        }

                    }

                }
                objSupplierForm.setSupplierListCollection(objSupplierList);
                if((SupplierForm)request.getSession().getAttribute("supplierForm") != null)
                {
                    flag = 1;
                }
            } else
            {
                SupplierForm objSupplierForm = (SupplierForm)request.getSession().getAttribute("supplierForm");System.out.println("VCNANIT objSupplierForm"+objSupplierForm);
                Vector objSupplierList = objSupplierForm.getSupplierListCollection();
                objSupplierForm.setSupplierListCollection(objSupplierList);
            }
            return "list";
        }
        catch(AppException e)
        {
            String errMsg = e.getSErrorDescription();
            request.setAttribute("PIX_ERROR", errMsg);
            return "error";
        }
        catch(Exception e)
        {
            AppException ae = new AppException();
            ae.performErrorAction("9000", "AdminCommand,executeSuppliersList", e);
            e.printStackTrace();
            String errMsg = ae.getSErrorDescription();
            request.setAttribute("PIX_ERROR", errMsg);
            return "error";
        }
    }

    private void executeSupplierRelatedList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        AdminDelegate objAdminDelegate = null;
        LinkedHashMap objHashMap = null;
        HashMap objHashMapCountry = null;
        HashMap objHashMapAccess = null;
        Vector supplierTypes = null;
        try
        {
            objAdminDelegate = new AdminDelegate();
            objHashMap = objAdminDelegate.getBasicPartyTransportInfo();
            HttpSession session = request.getSession();
            Set set = objHashMap.keySet();
            Iterator keyIter = set.iterator();
            Object key = keyIter.next();
            objHashMapAccess = (HashMap)objHashMap.get(key);
            Object key1 = keyIter.next();
            objHashMapCountry = (HashMap)objHashMap.get(key1);
            Object key2 = keyIter.next();
            supplierTypes = (Vector)objHashMap.get(key2);
            session.setAttribute("supplierListAccess", objHashMapAccess.get("ACCESS_METHOD"));
            session.setAttribute("supplierListCountry", objHashMapCountry.get("COUNTRY_LIST"));
            session.setAttribute("supplierTypes", supplierTypes);
        }
        catch(AppException e)
        {
            String errMsg = e.getSErrorDescription();
            request.setAttribute("PIX_ERROR", errMsg);
        }
    }

    private String executeSupplierInsert(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        SupplierForm objSupplierForm = (SupplierForm)form;
        AdminDelegate objAdminDelegate = new AdminDelegate();
        Party adminParty = objSupplierForm.getParty();
        PartyTransport objPartyTransport = new PartyTransport();
        Reference objReference = new Reference();
        Country objCountry = new Country();
        User objUser = null;
        Integer userId = null;
        try
        {
            Calendar cal = Calendar.getInstance();
            java.util.Date today = cal.getTime();
            if(request.getParameter("PAGE_VALUE") != "" && request.getParameter("PAGE_VALUE") != null)
            {
                String page_value = request.getParameter("PAGE_VALUE");
                String list_page = "?PAGE_VALUE=" + page_value;
                if((!PIXUtil.checkNullField(request.getParameter("sanFilter"))) & (!PIXUtil.checkNullField(request.getParameter("nameFilter"))) & (!PIXUtil.checkNullField(request.getParameter("statusFilter"))) & (!PIXUtil.checkNullField(request.getParameter("startDateFilter"))) & (!PIXUtil.checkNullField(request.getParameter("endDateFilter"))))
                {
                    request.setAttribute("OK_URL", "/admin/listsup.do?PAGE_VALUE=" + page_value + "&ADMIN_MODULE=SUP");
                } else
                {
                    if(request.getParameter("sanFilter") != "")
                    {
                        list_page = list_page + "&sanFilter=" + request.getParameter("sanFilter");
                    }
                    if(request.getParameter("nameFilter") != "")
                    {
                        list_page = list_page + "&nameFilter=" + request.getParameter("nameFilter");
                    }
                    if(request.getParameter("statusFilter") != "")
                    {
                        list_page = list_page + "&statusFilter=" + request.getParameter("statusFilter");
                    }
                    if(request.getParameter("startDateFilter") != "")
                    {
                        list_page = list_page + "&startDateFilter=" + request.getParameter("startDateFilter");
                    }
                    if(request.getParameter("endDateFilter") != "")
                    {
                        list_page = list_page + "&endDateFilter=" + request.getParameter("endDateFilter");
                    }
                    request.setAttribute("OK_URL", "/admin/listsup.do" + list_page + "&ADMIN_MODULE=SUP");
                }
            } else
            {
                request.setAttribute("OK_URL", "/admin/listsup.do?PAGE_VALUE=1&ADMIN_MODULE=SUP");
            }
            if(request.getSession().getAttribute("USER_INFO") != null)
            {
                objUser = (User)request.getSession().getAttribute("USER_INFO");
                userId = objUser.getUserId();
            }
            adminParty.setPartyType(adminParty.getPartyType());
            objPartyTransport.setSan(adminParty.getSan().trim());
            objReference.setRefId(adminParty.getTransportDetail().getAccessMethodDetail().getRefId());
            objPartyTransport.setAccessMethodDetail(objReference);
            if(adminParty.getTransportDetail().getServerName() != null)
            {
                objPartyTransport.setServerName(adminParty.getTransportDetail().getServerName().trim());
            }
            if(adminParty.getTransportDetail().getLogin() != null)
            {
                objPartyTransport.setLogin(adminParty.getTransportDetail().getLogin().trim());
            }
            if(adminParty.getTransportDetail().getPassword() != null)
            {
                objPartyTransport.setPassword(adminParty.getTransportDetail().getPassword().trim());
            }
            if(adminParty.getTransportDetail().getFolder() != null)
            {
                objPartyTransport.setFolder(adminParty.getTransportDetail().getFolder().trim());
            }
            if(adminParty.getTransportDetail().getPutFolder() != null)
            {
                objPartyTransport.setPutFolder(adminParty.getTransportDetail().getPutFolder().trim());
            }
            objPartyTransport.setCreationDateTime(today);
            objPartyTransport.setModDateTime(today);
            objPartyTransport.setCreatedBy(userId);
            objPartyTransport.setModifiedBy(userId);
            if(adminParty.getSan() != null)
            {
                adminParty.setSan(adminParty.getSan().trim());
            }
            if(adminParty.getName1() != null)
            {
                adminParty.setName1(adminParty.getName1().trim());
            }
            if(adminParty.getContactFirstName() != null)
            {
                adminParty.setContactFirstName(adminParty.getContactFirstName().trim());
            }
            if(adminParty.getContactLastName() != null)
            {
                adminParty.setContactLastName(adminParty.getContactLastName().trim());
            }
            if(adminParty.getPhone1() != null)
            {
                adminParty.setPhone1(adminParty.getPhone1().trim());
            }
            if(adminParty.getPhone2() != null)
            {
                adminParty.setPhone2(adminParty.getPhone2().trim());
            }
            if(adminParty.getFax1() != null)
            {
                adminParty.setFax1(adminParty.getFax1().trim());
            }
            if(adminParty.getFax2() != null)
            {
                adminParty.setFax2(adminParty.getFax2().trim());
            }
            if(adminParty.getMobile() != null)
            {
                adminParty.setMobile(adminParty.getMobile().trim());
            }
            if(adminParty.getAddress1() != null)
            {
                adminParty.setAddress1(adminParty.getAddress1().trim());
            }
            if(adminParty.getAddress2() != null)
            {
                adminParty.setAddress2(adminParty.getAddress2().trim());
            }
            if(adminParty.getAddress3() != null)
            {
                adminParty.setAddress3(adminParty.getAddress3().trim());
            }
            if(adminParty.getAddress4() != null)
            {
                adminParty.setAddress4(adminParty.getAddress4().trim());
            }
            if(adminParty.getState() != null)
            {
                adminParty.setState(adminParty.getState().trim());
            }
            if(adminParty.getCity() != null)
            {
                adminParty.setCity(adminParty.getCity().trim());
            }
            if(adminParty.getPostalCode() != null)
            {
                adminParty.setPostalCode(adminParty.getPostalCode().trim());
            }
            objCountry.setCountryCode(adminParty.getCountryDetail().getCountryCode());
            adminParty.setCountryDetail(objCountry);
            adminParty.setTransportDetail(objPartyTransport);
            if(adminParty.getEmail() != null)
            {
                adminParty.setEmail(adminParty.getEmail().trim());
            }
            if(adminParty.getWebsite() != null)
            {
                adminParty.setWebsite(adminParty.getWebsite().trim());
            }
            adminParty.setCreationDateTime(today);
            adminParty.setModDateTime(today);
            adminParty.setCreatedBy(userId);
            adminParty.setModifiedBy(userId);
            String san_no = objAdminDelegate.savePartyDetail(adminParty);
            String messageKey = "San No. " + san_no + " has been successfully saved.";
            request.setAttribute("SUCCESS_STRING", messageKey);
        }
        catch(AppException e)
        {
            String errMsg = e.getSErrorDescription();
            request.setAttribute("PIX_ERROR", errMsg);
            return "error";
        }
        catch(Exception e)
        {
            AppException ae = new AppException();
            ae.performErrorAction("9000", "AdminCommand,executeSupplierInsert", e);
            String errMsg = ae.getSErrorDescription();
            request.setAttribute("PIX_ERROR", errMsg);
            return "error";
        }
        return "insert";
    }

    private String executeSupplierDisplay(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        SupplierForm objSupplierForm = (SupplierForm)form;
        AdminDelegate objAdminDelegate = new AdminDelegate();
        Party party = null;
        String san = request.getParameter("SAN_VALUE_SUPPLIER");
        try
        {
            party = objAdminDelegate.displayPartyDetail(san);
            objSupplierForm.setParty(party);
            return "display";
        }
        catch(AppException e)
        {
            String errMsg = e.getSErrorDescription();
            request.setAttribute("PIX_ERROR", errMsg);
            return "error";
        }
        catch(Exception e)
        {
            AppException ae = new AppException();
            ae.performErrorAction("9000", "AdminCommand,executeSupplierDisplay", e);
            String errMsg = ae.getSErrorDescription();
            request.setAttribute("PIX_ERROR", errMsg);
            return "error";
        }
    }

    private String executeSupplierUpdate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        SupplierForm objSupplierForm = (SupplierForm)form;
        AdminDelegate objAdminDelegate = new AdminDelegate();
        Party adminParty = objSupplierForm.getParty();
        User objUser = null;
        Integer userId = null;
        try
        {
            Calendar cal = Calendar.getInstance();
            java.util.Date today = cal.getTime();
            if(request.getParameter("PAGE_VALUE") != "" && request.getParameter("PAGE_VALUE") != null)
            {
                String page_value = request.getParameter("PAGE_VALUE");
                String list_page = "?PAGE_VALUE=" + page_value;
                if((!PIXUtil.checkNullField(request.getParameter("sanFilter"))) & (!PIXUtil.checkNullField(request.getParameter("nameFilter"))) & (!PIXUtil.checkNullField(request.getParameter("statusFilter"))) & (!PIXUtil.checkNullField(request.getParameter("startDateFilter"))) & (!PIXUtil.checkNullField(request.getParameter("endDateFilter"))))
                {
                    request.setAttribute("OK_URL", "/admin/listsup.do?PAGE_VALUE=" + page_value + "&ADMIN_MODULE=SUP");
                } else
                {
                    if(request.getParameter("sanFilter") != "")
                    {
                        list_page = list_page + "&sanFilter=" + request.getParameter("sanFilter");
                    }
                    if(request.getParameter("nameFilter") != "")
                    {
                        list_page = list_page + "&nameFilter=" + request.getParameter("nameFilter");
                    }
                    if(request.getParameter("statusFilter") != "")
                    {
                        list_page = list_page + "&statusFilter=" + request.getParameter("statusFilter");
                    }
                    if(request.getParameter("startDateFilter") != "")
                    {
                        list_page = list_page + "&startDateFilter=" + request.getParameter("startDateFilter");
                    }
                    if(request.getParameter("endDateFilter") != "")
                    {
                        list_page = list_page + "&endDateFilter=" + request.getParameter("endDateFilter");
                    }
                    request.setAttribute("OK_URL", "/admin/listsup.do" + list_page + "&ADMIN_MODULE=SUP");
                }
            } else
            {
                request.setAttribute("OK_URL", "/admin/listsup.do?PAGE_VALUE=1&ADMIN_MODULE=SUP");
            }
            if(request.getSession().getAttribute("USER_INFO") != null)
            {
                objUser = (User)request.getSession().getAttribute("USER_INFO");
                userId = objUser.getUserId();
            }
            adminParty.setPartyType(adminParty.getPartyType());
            adminParty.setModDateTime(today);
            adminParty.setModifiedBy(userId);
            String san_no = objAdminDelegate.updatePartyDetail(adminParty);
            String messageKey = "San No. " + san_no + " has been successfully updated.";
            request.setAttribute("SUCCESS_STRING", messageKey);
        }
        catch(AppException e)
        {
            String errMsg = e.getSErrorDescription();
            request.setAttribute("PIX_ERROR", errMsg);
            return "error";
        }
        catch(Exception e)
        {
            AppException ae = new AppException();
            ae.performErrorAction("9000", "AdminCommand,executeSupplierUpdate", e);
            String errMsg = ae.getSErrorDescription();
            request.setAttribute("PIX_ERROR", errMsg);
            return "error";
        }
        return "update";
    }

    private String executeUsersList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        String objOrderBy = "CREATION_DATE_TIME";
        String objSort = "DESC";
        String startDate = null;
        String endDate = null;
        String startDateFilter = null;
        String endDateFilter = null;
        User user = null;
        AdminDelegate objAdminDelegate = null;
        String errMsg;
        try
        {
            UserForm objUserForm = (UserForm)form;
            user = new User();
            request.setAttribute("PATH", request.getContextPath());
            if(request.getSession().getAttribute("userList") != null)
            {
                request.getSession().removeAttribute("userList");
            }
            if(request.getSession().getAttribute("userListCountry") != null)
            {
                request.getSession().removeAttribute("userListCountry");
            }
            if("UserEditCancel".equals(request.getParameter("UserEditCancel")))
            {
                if(!PIXUtil.checkNullField(request.getParameter("userFilter")))
                {
                    user.setLogin(null);
                } else
                {
                    request.setAttribute("userFilter", request.getParameter("userFilter"));
                    String userFilter = request.getParameter("userFilter");
                    user.setLogin(userFilter);
                }
                user.setFirstName(null);
                user.setLastName(null);
                if(!PIXUtil.checkNullField(request.getParameter("accountStatusFilter")))
                {
                    user.setActiveFlag(null);
                } else
                {
                    request.setAttribute("accountStatusFilter", request.getParameter("accountStatusFilter"));
                    String accountStatusFilter = request.getParameter("accountStatusFilter");
                    user.setActiveFlag(accountStatusFilter);
                }
                if(PIXUtil.checkNullField(request.getParameter("accountTypeFilter")))
                {
                    request.setAttribute("accountTypeFilter", request.getParameter("accountTypeFilter"));
                    String accountTypeFilter = request.getParameter("accountTypeFilter");
                    UserRole objUserRoleNew = new UserRole();
                    objUserRoleNew.setRoleType(accountTypeFilter);
                    user.setRoleTypeDetail(objUserRoleNew);
                }
                if(PIXUtil.checkNullField(request.getParameter("startDateFilter")))
                {
                    request.setAttribute("startDateFilter", request.getParameter("startDateFilter"));
                    startDateFilter = request.getParameter("startDateFilter");
                }
                if(PIXUtil.checkNullField(request.getParameter("endDateFilter")))
                {
                    request.setAttribute("endDateFilter", request.getParameter("endDateFilter"));
                    endDateFilter = request.getParameter("endDateFilter");
                }
            } else
            {
                user.setLogin(objUserForm.getUser().getLogin());
                user.setFirstName(objUserForm.getUser().getFirstName());
                user.setLastName(objUserForm.getUser().getLastName());
                UserRole objUserRole = new UserRole();
                objUserRole.setRoleType(objUserForm.getUser().getRoleTypeDetail().getRoleType());
                user.setRoleTypeDetail(objUserRole);
                user.setActiveFlag(objUserForm.getUser().getActiveFlag());
                startDate = objUserForm.getStartDate();
                endDate = objUserForm.getEndDate();
                String userFilter = request.getParameter("user.login");
                String accountTypeFilter = request.getParameter("user.roleTypeDetail.roleType");
                String accountStatusFilter = request.getParameter("user.activeFlag");
                startDateFilter = objUserForm.getStartDate();
                endDateFilter = objUserForm.getEndDate();
                if(PIXUtil.checkNullField(userFilter))
                {
                    request.setAttribute("userFilter", userFilter);
                } else
                if(PIXUtil.checkNullField(request.getParameter("userFilter")))
                {
                    request.setAttribute("userFilter", request.getParameter("userFilter"));
                    userFilter = request.getParameter("userFilter");
                    user.setLogin(userFilter);
                }
                if(PIXUtil.checkNullField(accountTypeFilter))
                {
                    request.setAttribute("accountTypeFilter", accountTypeFilter);
                } else
                if(PIXUtil.checkNullField(request.getParameter("accountTypeFilter")))
                {
                    request.setAttribute("accountTypeFilter", request.getParameter("accountTypeFilter"));
                    accountTypeFilter = request.getParameter("accountTypeFilter");
                    UserRole objUserRoleNew = new UserRole();
                    objUserRoleNew.setRoleType(accountTypeFilter);
                    user.setRoleTypeDetail(objUserRoleNew);
                }
                if(PIXUtil.checkNullField(accountStatusFilter))
                {
                    request.setAttribute("accountStatusFilter", accountStatusFilter);
                } else
                if(PIXUtil.checkNullField(request.getParameter("accountStatusFilter")))
                {
                    request.setAttribute("accountStatusFilter", request.getParameter("accountStatusFilter"));
                    accountStatusFilter = request.getParameter("accountStatusFilter");
                    user.setActiveFlag(accountStatusFilter);
                }
                if(PIXUtil.checkNullField(startDateFilter))
                {
                    request.setAttribute("startDateFilter", startDateFilter);
                } else
                if(PIXUtil.checkNullField(request.getParameter("startDateFilter")))
                {
                    request.setAttribute("startDateFilter", request.getParameter("startDateFilter"));
                    startDateFilter = request.getParameter("startDateFilter");
                }
                if(PIXUtil.checkNullField(endDateFilter))
                {
                    request.setAttribute("endDateFilter", endDateFilter);
                } else
                if(PIXUtil.checkNullField(request.getParameter("endDateFilter")))
                {
                    request.setAttribute("endDateFilter", request.getParameter("endDateFilter"));
                    endDateFilter = request.getParameter("endDateFilter");
                }
            }
            objAdminDelegate = new AdminDelegate();
            startDate = startDateFilter;
            endDate = endDateFilter;
            int currentValue = Integer.parseInt(request.getParameter("PAGE_VALUE"));
            Vector objUserList = objAdminDelegate.displayUsersList(user, startDate, endDate, currentValue, objOrderBy, objSort);
            int size = objUserList != null ? objUserList.size() : 0;
            PIXUtil.getNextPage(request, currentValue, size);
            PIXUtil.getPrevPage(request, currentValue);
            if(size > 10)
            {
                objUserList.remove(objUserList.get(size - 1));
            }
            objUserForm.setUserCollection(objUserList);
            return "list";
        }
        catch(AppException e)
        {
            errMsg = e.getSErrorDescription();
        }
        request.setAttribute("PIX_ERROR", errMsg);
        return "error";
    }

    private String executeUserRelatedList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        AdminDelegate objAdminDelegate = null;
        HashMap objHashMap = null;
        HashMap objHashMapCountry = null;
        HashMap objHashMapAccount = null;
        try
        {
            objAdminDelegate = new AdminDelegate();
            objHashMap = objAdminDelegate.getBasicUserInfo();
            HttpSession session = request.getSession();
            Set set = objHashMap.keySet();
            Iterator keyIter = set.iterator();
            Object key = keyIter.next();
            objHashMapAccount = (HashMap)objHashMap.get(key);
            Object key1 = keyIter.next();
            objHashMapCountry = (HashMap)objHashMap.get(key1);
            session.setAttribute("userList", objHashMapAccount.get("ROLE_TYPE_LIST"));
            session.setAttribute("userListCountry", objHashMapCountry.get("COUNTRY_LIST"));
        }
        catch(AppException e)
        {
            String errMsg = e.getSErrorDescription();
            request.setAttribute("PIX_ERROR", errMsg);
            return "error";
        }
        return "relatedList";
    }

    private String executeUserInsert(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        UserForm objUserForm = (UserForm)form;
        AdminDelegate objAdminDelegate = new AdminDelegate();
        User objUser = objUserForm.getUser();
        Vector objVector = null;
        Reference objReference = new Reference();
        UserPriv objUserPriv = null;
        Country objCountry = new Country();
        User newUser = null;
        Integer userId = null;
        try
        {
            Calendar cal = Calendar.getInstance();
            java.util.Date today = cal.getTime();
            if(request.getParameter("PAGE_VALUE") != "" && request.getParameter("PAGE_VALUE") != null)
            {
                String page_value = request.getParameter("PAGE_VALUE");
                String list_page = "?PAGE_VALUE=" + page_value;
                if((!PIXUtil.checkNullField(request.getParameter("userFilter"))) & (!PIXUtil.checkNullField(request.getParameter("accountStatusFilter"))) & (!PIXUtil.checkNullField(request.getParameter("accountTypeFilter"))) & (!PIXUtil.checkNullField(request.getParameter("startDateFilter"))) & (!PIXUtil.checkNullField(request.getParameter("endDateFilter"))))
                {
                    request.setAttribute("OK_URL", "/admin/listuser.do?PAGE_VALUE=" + page_value + "&ADMIN_MODULE=USER");
                } else
                {
                    if(request.getParameter("userFilter") != "")
                    {
                        list_page = list_page + "&userFilter=" + request.getParameter("userFilter");
                    }
                    if(request.getParameter("accountStatusFilter") != "")
                    {
                        list_page = list_page + "&accountStatusFilter=" + request.getParameter("accountStatusFilter");
                    }
                    if(request.getParameter("accountTypeFilter") != "")
                    {
                        list_page = list_page + "&accountTypeFilter=" + request.getParameter("accountTypeFilter");
                    }
                    if(request.getParameter("startDateFilter") != "")
                    {
                        list_page = list_page + "&startDateFilter=" + request.getParameter("startDateFilter");
                    }
                    if(request.getParameter("endDateFilter") != "")
                    {
                        list_page = list_page + "&endDateFilter=" + request.getParameter("endDateFilter");
                    }
                    request.setAttribute("OK_URL", "/admin/listuser.do" + list_page + "&ADMIN_MODULE=USER");
                }
            } else
            {
                request.setAttribute("OK_URL", "/admin/listuser.do?PAGE_VALUE=1&ADMIN_MODULE=USER");
            }
            if(request.getSession().getAttribute("USER_INFO") != null)
            {
                newUser = (User)request.getSession().getAttribute("USER_INFO");
                userId = newUser.getUserId();
            }
            objAdminDelegate = new AdminDelegate();
            objVector = new Vector();
            objVector = objAdminDelegate.getUserModuleInfo();
            objUser.setPrivilegeCollection(new Vector());
            int vectorSize = objVector != null ? objVector.size() : 0;
            for(int i = 0; i < vectorSize; i++)
            {
                objUserPriv = new UserPriv();
                objReference = (Reference)objVector.get(i);
                if(objReference.getDescription().equals("Onhand Inventory"))
                {
                    if(objUserForm.getInventoryRead().equals("Y") && objUserForm.getInventoryPost().equals("Y"))
                    {
                        objUserPriv.setAccessFlag("B");
                    } else
                    if(objUserForm.getInventoryRead().equals("N") && objUserForm.getInventoryPost().equals("N"))
                    {
                        objUserPriv.setAccessFlag("N");
                    } else
                    if(objUserForm.getInventoryRead().equals("Y"))
                    {
                        objUserPriv.setAccessFlag("R");
                    } else
                    if(objUserForm.getInventoryPost().equals("Y"))
                    {
                        objUserPriv.setAccessFlag("W");
                    }
                    objUserPriv.setModuleDetail(objReference);
                    objUserPriv.setCreationDateTime(today);
                    objUserPriv.setModDateTime(today);
                    objUserPriv.setCreatedBy(userId);
                    objUserPriv.setModifiedBy(userId);
                    objUser.addToPrivilegeCollection(objUserPriv);
                } else
                if(objReference.getDescription().equals("Planning"))
                {
                    if(objUserForm.getPlanningRead().equals("Y") && objUserForm.getPlanningPost().equals("Y"))
                    {
                        objUserPriv.setAccessFlag("B");
                    } else
                    if(objUserForm.getPlanningRead().equals("N") && objUserForm.getPlanningPost().equals("N"))
                    {
                        objUserPriv.setAccessFlag("N");
                    } else
                    if(objUserForm.getPlanningRead().equals("Y"))
                    {
                        objUserPriv.setAccessFlag("R");
                    } else
                    if(objUserForm.getPlanningPost().equals("Y"))
                    {
                        objUserPriv.setAccessFlag("W");
                    }
                    objUserPriv.setModuleDetail(objReference);
                    objUserPriv.setCreationDateTime(today);
                    objUserPriv.setModDateTime(today);
                    objUserPriv.setCreatedBy(userId);
                    objUserPriv.setModifiedBy(userId);
                    objUser.addToPrivilegeCollection(objUserPriv);
                } else
                if(objReference.getDescription().equals("Book Specification"))
                {
                    if(objUserForm.getBookSpecRead().equals("Y") && objUserForm.getBookSpecPost().equals("Y"))
                    {
                        objUserPriv.setAccessFlag("B");
                    } else
                    if(objUserForm.getBookSpecRead().equals("N") && objUserForm.getBookSpecPost().equals("N"))
                    {
                        objUserPriv.setAccessFlag("N");
                    } else
                    if(objUserForm.getBookSpecRead().equals("Y"))
                    {
                        objUserPriv.setAccessFlag("R");
                    } else
                    if(objUserForm.getBookSpecPost().equals("Y"))
                    {
                        objUserPriv.setAccessFlag("W");
                    }
                    objUserPriv.setModuleDetail(objReference);
                    objUserPriv.setCreationDateTime(today);
                    objUserPriv.setModDateTime(today);
                    objUserPriv.setCreatedBy(userId);
                    objUserPriv.setModifiedBy(userId);
                    objUser.addToPrivilegeCollection(objUserPriv);
                } else
                if(objReference.getDescription().equals("Purchase Orders"))
                {
                    if(objUserForm.getPurchaseOrderRead().equals("Y") && objUserForm.getPurchaseOrderPost().equals("Y"))
                    {
                        objUserPriv.setAccessFlag("B");
                    } else
                    if(objUserForm.getPurchaseOrderRead().equals("N") && objUserForm.getPurchaseOrderPost().equals("N"))
                    {
                        objUserPriv.setAccessFlag("N");
                    } else
                    if(objUserForm.getPurchaseOrderRead().equals("Y"))
                    {
                        objUserPriv.setAccessFlag("R");
                    } else
                    if(objUserForm.getPurchaseOrderPost().equals("Y"))
                    {
                        objUserPriv.setAccessFlag("W");
                    }
                    objUserPriv.setModuleDetail(objReference);
                    objUserPriv.setCreationDateTime(today);
                    objUserPriv.setModDateTime(today);
                    objUserPriv.setCreatedBy(userId);
                    objUserPriv.setModifiedBy(userId);
                    objUser.addToPrivilegeCollection(objUserPriv);
                } else
                if(objReference.getDescription().equals("Order Confirmation"))
                {
                    if(objUserForm.getOrderConfirmRead().equals("Y") && objUserForm.getOrderConfirmPost().equals("Y"))
                    {
                        objUserPriv.setAccessFlag("B");
                    } else
                    if(objUserForm.getOrderConfirmRead().equals("N") && objUserForm.getOrderConfirmPost().equals("N"))
                    {
                        objUserPriv.setAccessFlag("N");
                    } else
                    if(objUserForm.getOrderConfirmRead().equals("Y"))
                    {
                        objUserPriv.setAccessFlag("R");
                    } else
                    if(objUserForm.getOrderConfirmPost().equals("Y"))
                    {
                        objUserPriv.setAccessFlag("W");
                    }
                    objUserPriv.setModuleDetail(objReference);
                    objUserPriv.setCreationDateTime(today);
                    objUserPriv.setModDateTime(today);
                    objUserPriv.setCreatedBy(userId);
                    objUserPriv.setModifiedBy(userId);
                    objUser.addToPrivilegeCollection(objUserPriv);
                } else
                if(objReference.getDescription().equals("Order Status"))
                {
                    if(objUserForm.getOrderStatusRead().equals("Y") && objUserForm.getOrderStatusPost().equals("Y"))
                    {
                        objUserPriv.setAccessFlag("B");
                    } else
                    if(objUserForm.getOrderStatusRead().equals("N") && objUserForm.getOrderStatusPost().equals("N"))
                    {
                        objUserPriv.setAccessFlag("N");
                    } else
                    if(objUserForm.getOrderStatusRead().equals("Y"))
                    {
                        objUserPriv.setAccessFlag("R");
                    } else
                    if(objUserForm.getOrderStatusPost().equals("Y"))
                    {
                        objUserPriv.setAccessFlag("W");
                    }
                    objUserPriv.setModuleDetail(objReference);
                    objUserPriv.setCreationDateTime(today);
                    objUserPriv.setModDateTime(today);
                    objUserPriv.setCreatedBy(userId);
                    objUserPriv.setModifiedBy(userId);
                    objUser.addToPrivilegeCollection(objUserPriv);
                } else
                if(objReference.getDescription().equals("Delivery Message"))
                {
                    if(objUserForm.getDeliveryMesgRead().equals("Y") && objUserForm.getDeliveryMesgPost().equals("Y"))
                    {
                        objUserPriv.setAccessFlag("B");
                    } else
                    if(objUserForm.getDeliveryMesgRead().equals("N") && objUserForm.getDeliveryMesgPost().equals("N"))
                    {
                        objUserPriv.setAccessFlag("N");
                    } else
                    if(objUserForm.getDeliveryMesgRead().equals("Y"))
                    {
                        objUserPriv.setAccessFlag("R");
                    } else
                    if(objUserForm.getDeliveryMesgPost().equals("Y"))
                    {
                        objUserPriv.setAccessFlag("W");
                    }
                    objUserPriv.setModuleDetail(objReference);
                    objUserPriv.setCreationDateTime(today);
                    objUserPriv.setModDateTime(today);
                    objUserPriv.setCreatedBy(userId);
                    objUserPriv.setModifiedBy(userId);
                    objUser.addToPrivilegeCollection(objUserPriv);
                } else
                if(objReference.getDescription().equals("Goods Receipt"))
                {
                    if(objUserForm.getGoodsReceiptRead().equals("Y") && objUserForm.getGoodsReceiptPost().equals("Y"))
                    {
                        objUserPriv.setAccessFlag("B");
                    } else
                    if(objUserForm.getGoodsReceiptRead().equals("N") && objUserForm.getGoodsReceiptPost().equals("N"))
                    {
                        objUserPriv.setAccessFlag("N");
                    } else
                    if(objUserForm.getGoodsReceiptRead().equals("Y"))
                    {
                        objUserPriv.setAccessFlag("R");
                    } else
                    if(objUserForm.getGoodsReceiptPost().equals("Y"))
                    {
                        objUserPriv.setAccessFlag("W");
                    }
                    objUserPriv.setModuleDetail(objReference);
                    objUserPriv.setCreationDateTime(today);
                    objUserPriv.setModDateTime(today);
                    objUserPriv.setCreatedBy(userId);
                    objUserPriv.setModifiedBy(userId);
                    objUser.addToPrivilegeCollection(objUserPriv);
                } else
                if(objReference.getDescription().equals("Usage"))
                {
                    if(objUserForm.getUsageRead().equals("Y") && objUserForm.getUsagePost().equals("Y"))
                    {
                        objUserPriv.setAccessFlag("B");
                    } else
                    if(objUserForm.getUsageRead().equals("N") && objUserForm.getUsagePost().equals("N"))
                    {
                        objUserPriv.setAccessFlag("N");
                    } else
                    if(objUserForm.getUsageRead().equals("Y"))
                    {
                        objUserPriv.setAccessFlag("R");
                    } else
                    if(objUserForm.getUsagePost().equals("Y"))
                    {
                        objUserPriv.setAccessFlag("W");
                    }
                    objUserPriv.setModuleDetail(objReference);
                    objUserPriv.setCreationDateTime(today);
                    objUserPriv.setModDateTime(today);
                    objUserPriv.setCreatedBy(userId);
                    objUserPriv.setModifiedBy(userId);
                    objUser.addToPrivilegeCollection(objUserPriv);  
                }
                else
                    if(objReference.getDescription().equals("ARP Title SetUp"))
                    {
                        if(objUserForm.getArpTitleRead().equals("Y") && objUserForm.getArpTitlePost().equals("Y"))
                        {
                            objUserPriv.setAccessFlag("B");
                        } else
                        if(objUserForm.getArpTitleRead().equals("N") && objUserForm.getArpTitlePost().equals("N"))
                        {
                            objUserPriv.setAccessFlag("N");
                        } else
                        if(objUserForm.getArpTitleRead().equals("Y"))
                        {
                            objUserPriv.setAccessFlag("R");
                        } else
                        if(objUserForm.getArpTitlePost().equals("Y"))
                        {
                            objUserPriv.setAccessFlag("W");
                        }
                        objUserPriv.setModuleDetail(objReference);
                        objUserPriv.setCreationDateTime(today);
                        objUserPriv.setModDateTime(today);
                        objUserPriv.setCreatedBy(userId);
                        objUserPriv.setModifiedBy(userId);  
                        objUser.addToPrivilegeCollection(objUserPriv);
                    }
                    else
                        if(objReference.getDescription().equals("Dropship"))
                        {
                            if(objUserForm.getDropshipinstructionsRead().equals("Y") && objUserForm.getDropshipinstructionsPost().equals("Y"))
                            {
                                objUserPriv.setAccessFlag("B");
                            } else
                            if(objUserForm.getDropshipinstructionsRead().equals("N") && objUserForm.getDropshipinstructionsPost().equals("N"))
                            {
                                objUserPriv.setAccessFlag("N");
                            } else
                            if(objUserForm.getDropshipinstructionsRead().equals("Y"))
                            {
                                objUserPriv.setAccessFlag("R");
                            } else
                            if(objUserForm.getDropshipinstructionsPost().equals("Y"))
                            {
                                objUserPriv.setAccessFlag("W");
                            }
                            objUserPriv.setModuleDetail(objReference);
                            objUserPriv.setCreationDateTime(today);
                            objUserPriv.setModDateTime(today);
                            objUserPriv.setCreatedBy(userId);
                            objUserPriv.setModifiedBy(userId);  
                            objUser.addToPrivilegeCollection(objUserPriv);
                        }
                       
            }

            if(objUser.getLogin() != null)
            {
                objUser.setLogin(objUser.getLogin().trim());
            }
            if(objUser.getFirstName() != null)
            {
                objUser.setFirstName(objUser.getFirstName().trim());
            }
            if(objUser.getLastName() != null)
            {
                objUser.setLastName(objUser.getLastName().trim());
            }
            objUser.setPassword("Pearson");
//            if(objUser.getPassword() != null)
//            {
//                objUser.setPassword(objUser.getPassword().trim());
//            }
            if(objUser.getPhone1() != null)
            {
                objUser.setPhone1(objUser.getPhone1().trim());
            }
            if(objUser.getPhone2() != null)
            {
                objUser.setPhone2(objUser.getPhone2().trim());
            }
            if(objUser.getFax1() != null)
            {
                objUser.setFax1(objUser.getFax1().trim());
            }
            if(objUser.getFax2() != null)
            {
                objUser.setFax2(objUser.getFax2().trim());
            }
            if(objUser.getAddress1() != null)
            {
                objUser.setAddress1(objUser.getAddress1().trim());
            }
            if(objUser.getAddress2() != null)
            {
                objUser.setAddress2(objUser.getAddress2().trim());
            }
            if(objUser.getAddress3() != null)
            {
                objUser.setAddress3(objUser.getAddress3().trim());
            }
            if(objUser.getAddress4() != null)
            {
                objUser.setAddress4(objUser.getAddress4().trim());
            }
            if(objUser.getState() != null)
            {
                objUser.setState(objUser.getState().trim());
            }
            if(objUser.getCity() != null)
            {
                objUser.setCity(objUser.getCity().trim());
            }
            if(objUser.getPostalCode() != null)
            {
                objUser.setPostalCode(objUser.getPostalCode().trim());
            }
            if(objUser.getEmail() != null)
            {
                objUser.setEmail(objUser.getEmail().trim());
            }
            if(objUser.getMobile() != null)
            {
                objUser.setMobile(objUser.getMobile().trim());
            }
            if(objUser.getWebsite() != null)
            {
                objUser.setWebsite(objUser.getWebsite().trim());
            }
            UserRole objUserRole = new UserRole();
            objUserRole.setRoleType(objUser.getRoleTypeDetail().getRoleType());
            objUser.setRoleTypeDetail(objUserRole);
            objCountry.setCountryCode(objUser.getCountryDetail().getCountryCode());
            objUser.setCountryDetail(objCountry);
            objUser.setCreationDateTime(today);
            objUser.setModDateTime(today);
            objUser.setCreatedBy(userId);
            objUser.setModifiedBy(userId);
            objUser.setPasswordExpiry("N");
            String login = objAdminDelegate.saveUserDetail(objUser);
            String messageKey = "User " + login + " has been successfully saved.";
            request.setAttribute("SUCCESS_STRING", messageKey);
        }
        catch(AppException e)
        {
            String errMsg = e.getSErrorDescription();
            request.setAttribute("PIX_ERROR", errMsg);
            return "error";
        }
        catch(Exception e)
        {
            AppException ae = new AppException();
            ae.performErrorAction("9000", "AdminCommand,executeUserInsert", e);
            String errMsg = ae.getSErrorDescription();
            request.setAttribute("PIX_ERROR", errMsg);
            return "error";
        }
        return "insert";
    }

    public String executeGeneral(String actioncommand, ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, ActionMessages messages)
    {
        Party objParty = null;
        UserForm objUserForm = (UserForm)form;
        User objUser = objUserForm.getUser();
        Vector objPartyCollection = null;
        try
        {System.out.println("request.getParameter(\"supplierIds\")---"+request.getParameter("supplierIds")+"<------");
            if(! "".equals(request.getParameter("supplierIds")))
            {
                String indexesSupplier = request.getParameter("supplierIds");
                StringTokenizer objSplitString = new StringTokenizer(indexesSupplier, ",");
                SupplierForm objSupplierForm=null;
                objSupplierForm = (SupplierForm)request.getSession().getAttribute("supplierForm");System.out.println("VCNANIT objSupplierForm.toString()"+objSupplierForm.toString());System.out.println("VCNANIT (SupplierForm)request.getSession().getAttribute(\"supplierForm\")"+(SupplierForm)request.getSession().getAttribute("supplierForm"));
                Vector objSupplierList = objSupplierForm.getSupplierListCollection();
                while(objSplitString.hasMoreTokens()) 
                {
                    String key = objSplitString.nextToken();
                    if(objSupplierList.size() != Integer.parseInt(key) && objSupplierList.size() > Integer.parseInt(key))
                    {
                        objParty = (Party)objSupplierList.get(Integer.parseInt(key));
                        objParty.setPartyType(objParty.getPartyType());
                        objUser.addToPartyCollection(objParty);
                    }
                }
                objPartyCollection = (Vector)objUser.getPartyCollection();
                int partyCollectionSize = objPartyCollection != null ? objPartyCollection.size() : 0;
                for(int j = 0; j < partyCollectionSize; j++)
                {
                    Party newParty = (Party)objPartyCollection.elementAt(j);
                    for(int i = 0; i < objSupplierList.size(); i++)
                    {
                        Party supplierParty = (Party)objSupplierList.get(i);
                        if(newParty.getSan().equals(supplierParty.getSan()))
                        {
                            objSupplierList.remove(supplierParty);
                        }
                    }

                }

                objSupplierForm.setSupplierListCollection(objSupplierList);
                request.getSession().setAttribute("supplierForm", objSupplierForm);
                objUser.setPartyCollection(objPartyCollection);
            } else
            if( ! "".equals(request.getParameter("publisherIds")))
            {
                String indexesPublisher = request.getParameter("publisherIds");
                StringTokenizer objSplitStringPublisher = new StringTokenizer(indexesPublisher, ",");
                AddPubUnitForm objPublisherForm = (AddPubUnitForm)request.getSession().getAttribute("addPubUnitForm");
                Vector objPublisherList = objPublisherForm.getPubUnitListCollection();
                while(objSplitStringPublisher.hasMoreTokens()) 
                {
                    String key = objSplitStringPublisher.nextToken();
                    if(objPublisherList.size() != Integer.parseInt(key) && objPublisherList.size() > Integer.parseInt(key))
                    {
                        objParty = (Party)objPublisherList.get(Integer.parseInt(key));
                        objParty.setPartyType("B");
                        objUser.addToPartyCollection(objParty);
                    }
                }
                objPartyCollection = (Vector)objUser.getPartyCollection();
                int partyCollectionSize = objPartyCollection != null ? objPartyCollection.size() : 0;
                for(int j = 0; j < partyCollectionSize; j++)
                {
                    Party newParty = (Party)objPartyCollection.elementAt(j);
                    for(int i = 0; i < objPublisherList.size(); i++)
                    {
                        Party publisherParty = (Party)objPublisherList.get(i);
                        if(newParty.getSan().equals(publisherParty.getSan()))
                        {
                            objPublisherList.remove(publisherParty);
                        }
                    }

                }

                objPublisherForm.setPubUnitListCollection(objPublisherList);
                request.getSession().setAttribute("addPubUnitForm", objPublisherForm);
            }
            objUserForm.setUser(objUser);
            return "general";
        }
        catch(Exception e)
        {
            AppException ae = new AppException();
            ae.performErrorAction("9000", "AdminCommand,executeGeneral", e);
            String errMsg = ae.getSErrorDescription();
            e.printStackTrace();
            request.setAttribute("PIX_ERROR", errMsg);
            return "error";
        }
    }
    
    private String executeModifiedUserDisplay(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
    	UserForm objUserForm = (UserForm)form;
        AdminDelegate objAdminDelegate = new AdminDelegate();
        User objUser = null;
        Vector objVector = null;
        Vector objPrivilegeVector = null;
        Integer login = null;
        HttpSession session = request.getSession();
        if(session.getAttribute("LOGIN") != null)
        {
            User editUser = (User)request.getSession().getAttribute("USER_INFO");
            login = editUser.getUserId();
        } else
        if("EDITPROFILE".equals(request.getParameter("LOGIN")))
        {
            User editUser = (User)request.getSession().getAttribute("USER_INFO");
            login = editUser.getUserId();
        } else
        {
            login = new Integer(Integer.parseInt(request.getParameter("LOGIN")));
        }
        
        String tokenTab = (String)request.getParameter("tokenTab");

        UserPriv objUserPriv = null;
        Reference objReference = new Reference();
        try
        {
        	
            objUser = objAdminDelegate.displayModifiedUserDetail(login, tokenTab);
            objPrivilegeVector = (Vector)objUser.getPrivilegeCollection();
            objVector = new Vector();
//            objVector = objAdminDelegate.getUserModuleInfo();
            
            String ssoid = objUser.getSsoid();
            String subSsoid = null;
            if(ssoid != null){
                int idx = ssoid.indexOf("@");
                if(idx != -1){
                	subSsoid = ssoid.substring(0,idx);
                }
                else
                	subSsoid = ssoid;
                
                objUserForm.setV42(subSsoid);
            }
            
            int privilegeCollectionSize = objPrivilegeVector != null ? objPrivilegeVector.size() : 0;
            request.getSession().setAttribute("Password", objUser.getPassword());
            
            for(int j = 0; j < privilegeCollectionSize; j++)
            {
                objUserPriv = (UserPriv)objPrivilegeVector.get(j);
                if(objUserPriv!=null)
                {
                    objReference = (Reference)objUserPriv.getModuleDetail();
                    if(objReference.getDescription().equals("Onhand Inventory"))
                    {
                            if(objUserPriv.getAccessFlag().equals("B"))
                            {
                                objUserForm.setInventoryRead("Y");
                                objUserForm.setInventoryPost("Y");
                            } else
                            if(objUserPriv.getAccessFlag().equals("N"))
                            {
                                objUserForm.setInventoryRead("N");
                                objUserForm.setInventoryPost("N");
                            } else
                            if(objUserPriv.getAccessFlag().equals("R"))
                            {
                                objUserForm.setInventoryRead("Y");
                                objUserForm.setInventoryPost("N");
                            } else
                            if(objUserPriv.getAccessFlag().equals("W"))
                            {
                                objUserForm.setInventoryRead("N");
                                objUserForm.setInventoryPost("Y");
                            }
                    } else
                    if(objReference.getDescription().equals("Planning"))
                    {
                            if(objUserPriv.getAccessFlag().equals("B"))
                            {
                                objUserForm.setPlanningRead("Y");
                                objUserForm.setPlanningPost("Y");
                            } else
                            if(objUserPriv.getAccessFlag().equals("N"))
                            {
                                objUserForm.setPlanningRead("N");
                                objUserForm.setPlanningPost("N");
                            } else
                            if(objUserPriv.getAccessFlag().equals("R"))
                            {
                                objUserForm.setPlanningRead("Y");
                                objUserForm.setPlanningPost("N");
                            } else
                            if(objUserPriv.getAccessFlag().equals("W"))
                            {
                                objUserForm.setPlanningRead("N");
                                objUserForm.setPlanningPost("Y");
                            }
                    } else
                    if(objReference.getDescription().equals("Book Specification"))
                    {
                            if(objUserPriv.getAccessFlag().equals("B"))
                            {
                                objUserForm.setBookSpecRead("Y");
                                objUserForm.setBookSpecPost("Y");
                            } else
                            if(objUserPriv.getAccessFlag().equals("N"))
                            {
                                objUserForm.setBookSpecRead("N");
                                objUserForm.setBookSpecPost("N");
                            } else
                            if(objUserPriv.getAccessFlag().equals("R"))
                            {
                                objUserForm.setBookSpecRead("Y");
                                objUserForm.setBookSpecPost("N");
                            } else
                            if(objUserPriv.getAccessFlag().equals("W"))
                            {
                                objUserForm.setBookSpecRead("N");
                                objUserForm.setBookSpecPost("Y");
                            }
                    } else
                    if(objReference.getDescription().equals("Purchase Orders"))
                    {
                            if(objUserPriv.getAccessFlag().equals("B"))
                            {
                                objUserForm.setPurchaseOrderRead("Y");
                                objUserForm.setPurchaseOrderPost("Y");
                            } else
                            if(objUserPriv.getAccessFlag().equals("N"))
                            {
                                objUserForm.setPurchaseOrderRead("N");
                                objUserForm.setPurchaseOrderPost("N");
                            } else
                            if(objUserPriv.getAccessFlag().equals("R"))
                            {
                                objUserForm.setPurchaseOrderRead("Y");
                                objUserForm.setPurchaseOrderPost("N");
                            } else
                            if(objUserPriv.getAccessFlag().equals("W"))
                            {
                                objUserForm.setPurchaseOrderRead("N");
                                objUserForm.setPurchaseOrderPost("Y");
                            }
                    } else
                    if(objReference.getDescription().equals("Order Confirmation"))
                    {
                            if(objUserPriv.getAccessFlag().equals("B"))
                            {
                                objUserForm.setOrderConfirmRead("Y");
                                objUserForm.setOrderConfirmPost("Y");
                            } else
                            if(objUserPriv.getAccessFlag().equals("N"))
                            {
                                objUserForm.setOrderConfirmRead("N");
                                objUserForm.setOrderConfirmPost("N");
                            } else
                            if(objUserPriv.getAccessFlag().equals("R"))
                            {
                                objUserForm.setOrderConfirmRead("Y");
                                objUserForm.setOrderConfirmPost("N");
                            } else
                            if(objUserPriv.getAccessFlag().equals("W"))
                            {
                                objUserForm.setOrderConfirmRead("N");
                                objUserForm.setOrderConfirmPost("Y");
                            }
                    } else
                    if(objReference.getDescription().equals("Order Status"))
                    {
                            if(objUserPriv.getAccessFlag().equals("B"))
                            {
                                objUserForm.setOrderStatusRead("Y");
                                objUserForm.setOrderStatusPost("Y");
                            } else
                            if(objUserPriv.getAccessFlag().equals("N"))
                            {
                                objUserForm.setOrderStatusRead("N");
                                objUserForm.setOrderStatusPost("N");
                            } else
                            if(objUserPriv.getAccessFlag().equals("R"))
                            {
                                objUserForm.setOrderStatusRead("Y");
                                objUserForm.setOrderStatusPost("N");
                            } else
                            if(objUserPriv.getAccessFlag().equals("W"))
                            {
                                objUserForm.setOrderStatusRead("N");
                                objUserForm.setOrderStatusPost("Y");
                            }
                    } else
                    if(objReference.getDescription().equals("Delivery Message"))
                    {
                            if(objUserPriv.getAccessFlag().equals("B"))
                            {
                                objUserForm.setDeliveryMesgRead("Y");
                                objUserForm.setDeliveryMesgPost("Y");
                            } else
                            if(objUserPriv.getAccessFlag().equals("N"))
                            {
                                objUserForm.setDeliveryMesgRead("N");
                                objUserForm.setDeliveryMesgPost("N");
                            } else
                            if(objUserPriv.getAccessFlag().equals("R"))
                            {
                                objUserForm.setDeliveryMesgRead("Y");
                                objUserForm.setDeliveryMesgPost("N");
                            } else
                            if(objUserPriv.getAccessFlag().equals("W"))
                            {
                                objUserForm.setDeliveryMesgRead("N");
                                objUserForm.setDeliveryMesgPost("Y");
                            }
                    } else
                    if(objReference.getDescription().equals("Goods Receipt"))
                    {
                            if(objUserPriv.getAccessFlag().equals("B"))
                            {
                                objUserForm.setGoodsReceiptRead("Y");
                                objUserForm.setGoodsReceiptPost("Y");
                            } else
                            if(objUserPriv.getAccessFlag().equals("N"))
                            {
                                objUserForm.setGoodsReceiptRead("N");
                                objUserForm.setGoodsReceiptPost("N");
                            } else
                            if(objUserPriv.getAccessFlag().equals("R"))
                            {
                                objUserForm.setGoodsReceiptRead("Y");
                                objUserForm.setGoodsReceiptPost("N");
                            } else
                            if(objUserPriv.getAccessFlag().equals("W"))
                            {
                                objUserForm.setGoodsReceiptRead("N");
                                objUserForm.setGoodsReceiptPost("Y");
                            }
                    } else
                    if(objReference.getDescription().equals("Usage"))
                    {
                        if(objUserPriv.getAccessFlag().equals("B"))
                        {
                            objUserForm.setUsageRead("Y");
                            objUserForm.setUsagePost("Y");
                        } else
                        if(objUserPriv.getAccessFlag().equals("N"))
                        {
                            objUserForm.setUsageRead("N");
                            objUserForm.setUsagePost("N");
                        } else
                        if(objUserPriv.getAccessFlag().equals("R"))
                        {
                            objUserForm.setUsageRead("Y");
                            objUserForm.setUsagePost("N");
                        } else
                        if(objUserPriv.getAccessFlag().equals("W"))
                        {
                            objUserForm.setUsageRead("N");
                            objUserForm.setUsagePost("Y");
                        }
                    }
                    else
                        if(objReference.getDescription().equals("ARP Title SetUp"))
                        {
                                if(objUserPriv.getAccessFlag().equals("B"))
                                {
                                    objUserForm.setArpTitleRead("Y");
                                    objUserForm.setArpTitlePost("Y");
                                } else
                                if(objUserPriv.getAccessFlag().equals("N"))
                                {
                                    objUserForm.setArpTitleRead("N");
                                    objUserForm.setArpTitlePost("N");
                                } else
                                if(objUserPriv.getAccessFlag().equals("R"))
                                {
                                    objUserForm.setArpTitleRead("Y");
                                    objUserForm.setArpTitlePost("N");
                                } else
                                if(objUserPriv.getAccessFlag().equals("W"))
                                {
                                    objUserForm.setArpTitleRead("N");
                                    objUserForm.setArpTitlePost("Y");
                                }
                        } else
                            if(objReference.getDescription().equals("Dropship"))
                            {
                                    if(objUserPriv.getAccessFlag().equals("B"))
                                    {
                                        objUserForm.setDropshipinstructionsRead("Y");
                                        objUserForm.setDropshipinstructionsPost("Y");
                                    } else
                                    if(objUserPriv.getAccessFlag().equals("N"))
                                    {
                                        objUserForm.setDropshipinstructionsRead("N");
                                        objUserForm.setDropshipinstructionsPost("N");
                                    } else
                                    if(objUserPriv.getAccessFlag().equals("R"))
                                    {
                                        objUserForm.setDropshipinstructionsRead("Y");
                                        objUserForm.setDropshipinstructionsPost("N");
                                    } else
                                    if(objUserPriv.getAccessFlag().equals("W"))
                                    {
                                        objUserForm.setDropshipinstructionsRead("N");
                                        objUserForm.setDropshipinstructionsPost("Y");
                                    }
                            }
               
                }

            }
            
	    	objUserForm.setUser(objUser);
	        return "display";
	    }
	    catch(AppException e)
	    {
	        String errMsg = e.getSErrorDescription();
	        request.setAttribute("PIX_ERROR", errMsg);
	        return "error";
	    }
	    catch(Exception e)
	    {
	        AppException ae = new AppException();
	        ae.performErrorAction("9000", "AdminCommand,executeUserDisplay", e);
	        String errMsg = ae.getSErrorDescription();
	        request.setAttribute("PIX_ERROR", errMsg);
	        e.printStackTrace();
	        return "error";
	    }
    }

    private String executeUserDisplay(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        UserForm objUserForm = (UserForm)form;
        AdminDelegate objAdminDelegate = new AdminDelegate();
        User objUser = null;
        Vector objVector = null;
        Vector objPrivilegeVector = null;
        Integer login = null;
        HttpSession session = request.getSession();
        if(session.getAttribute("LOGIN") != null)
        {
            User editUser = (User)request.getSession().getAttribute("USER_INFO");
            login = editUser.getUserId();
        } else
        if("EDITPROFILE".equals(request.getParameter("LOGIN")))
        {
            User editUser = (User)request.getSession().getAttribute("USER_INFO");
            login = editUser.getUserId();
        } else
        {
            login = new Integer(Integer.parseInt(request.getParameter("LOGIN")));
        }
        
        UserPriv objUserPriv = null;
        Reference objReference = new Reference();
        try
        {
        	
            objUser = objAdminDelegate.displayUserDetail(login);
            objPrivilegeVector = (Vector)objUser.getPrivilegeCollection();
            objVector = new Vector();
            objVector = objAdminDelegate.getUserModuleInfo();
            
            String ssoid = objUser.getSsoid();
            String subSsoid = null;
            if(ssoid != null){
                int idx = ssoid.indexOf("@");
                if(idx != -1){
                	subSsoid = ssoid.substring(0,idx);
                }
                else
                	subSsoid = ssoid;
                
                objUserForm.setV42(subSsoid);
            }

            
            int privilegeCollectionSize = objPrivilegeVector != null ? objPrivilegeVector.size() : 0;
            request.getSession().setAttribute("Password", objUser.getPassword());
            for(int j = 0; j < privilegeCollectionSize; j++)
            {
                objUserPriv = (UserPriv)objPrivilegeVector.get(j);
                int vectorSize = objVector != null ? objVector.size() : 0;
                for(int i = 0; i < vectorSize; i++)
                {
                    objReference = (Reference)objVector.get(i);
                    if(objReference.getDescription().equals("Onhand Inventory"))
                    {
                        if(objReference.getRefId().equals(objUserPriv.getModuleDetail().getRefId()) && objUser.getUserId().equals(objUserPriv.getUserDetail().getUserId()))
                        {
                            if(objUserPriv.getAccessFlag().equals("B"))
                            {
                                objUserForm.setInventoryRead("Y");
                                objUserForm.setInventoryPost("Y");
                            } else
                            if(objUserPriv.getAccessFlag().equals("N"))
                            {
                                objUserForm.setInventoryRead("N");
                                objUserForm.setInventoryPost("N");
                            } else
                            if(objUserPriv.getAccessFlag().equals("R"))
                            {
                                objUserForm.setInventoryRead("Y");
                                objUserForm.setInventoryPost("N");
                            } else
                            if(objUserPriv.getAccessFlag().equals("W"))
                            {
                                objUserForm.setInventoryRead("N");
                                objUserForm.setInventoryPost("Y");
                            }
                        }
                    } else
                    if(objReference.getDescription().equals("Planning"))
                    {
                        if(objReference.getRefId().equals(objUserPriv.getModuleDetail().getRefId()) && objUser.getUserId().equals(objUserPriv.getUserDetail().getUserId()))
                        {
                            if(objUserPriv.getAccessFlag().equals("B"))
                            {
                                objUserForm.setPlanningRead("Y");
                                objUserForm.setPlanningPost("Y");
                            } else
                            if(objUserPriv.getAccessFlag().equals("N"))
                            {
                                objUserForm.setPlanningRead("N");
                                objUserForm.setPlanningPost("N");
                            } else
                            if(objUserPriv.getAccessFlag().equals("R"))
                            {
                                objUserForm.setPlanningRead("Y");
                                objUserForm.setPlanningPost("N");
                            } else
                            if(objUserPriv.getAccessFlag().equals("W"))
                            {
                                objUserForm.setPlanningRead("N");
                                objUserForm.setPlanningPost("Y");
                            }
                        }
                    } else
                    if(objReference.getDescription().equals("Book Specification"))
                    {
                        if(objReference.getRefId().equals(objUserPriv.getModuleDetail().getRefId()) && objUser.getUserId().equals(objUserPriv.getUserDetail().getUserId()))
                        {
                            if(objUserPriv.getAccessFlag().equals("B"))
                            {
                                objUserForm.setBookSpecRead("Y");
                                objUserForm.setBookSpecPost("Y");
                            } else
                            if(objUserPriv.getAccessFlag().equals("N"))
                            {
                                objUserForm.setBookSpecRead("N");
                                objUserForm.setBookSpecPost("N");
                            } else
                            if(objUserPriv.getAccessFlag().equals("R"))
                            {
                                objUserForm.setBookSpecRead("Y");
                                objUserForm.setBookSpecPost("N");
                            } else
                            if(objUserPriv.getAccessFlag().equals("W"))
                            {
                                objUserForm.setBookSpecRead("N");
                                objUserForm.setBookSpecPost("Y");
                            }
                        }
                    } else
                    if(objReference.getDescription().equals("Purchase Orders"))
                    {
                        if(objReference.getRefId().equals(objUserPriv.getModuleDetail().getRefId()) && objUser.getUserId().equals(objUserPriv.getUserDetail().getUserId()))
                        {
                            if(objUserPriv.getAccessFlag().equals("B"))
                            {
                                objUserForm.setPurchaseOrderRead("Y");
                                objUserForm.setPurchaseOrderPost("Y");
                            } else
                            if(objUserPriv.getAccessFlag().equals("N"))
                            {
                                objUserForm.setPurchaseOrderRead("N");
                                objUserForm.setPurchaseOrderPost("N");
                            } else
                            if(objUserPriv.getAccessFlag().equals("R"))
                            {
                                objUserForm.setPurchaseOrderRead("Y");
                                objUserForm.setPurchaseOrderPost("N");
                            } else
                            if(objUserPriv.getAccessFlag().equals("W"))
                            {
                                objUserForm.setPurchaseOrderRead("N");
                                objUserForm.setPurchaseOrderPost("Y");
                            }
                        }
                    } else
                    if(objReference.getDescription().equals("Order Confirmation"))
                    {
                        if(objReference.getRefId().equals(objUserPriv.getModuleDetail().getRefId()) && objUser.getUserId().equals(objUserPriv.getUserDetail().getUserId()))
                        {
                            if(objUserPriv.getAccessFlag().equals("B"))
                            {
                                objUserForm.setOrderConfirmRead("Y");
                                objUserForm.setOrderConfirmPost("Y");
                            } else
                            if(objUserPriv.getAccessFlag().equals("N"))
                            {
                                objUserForm.setOrderConfirmRead("N");
                                objUserForm.setOrderConfirmPost("N");
                            } else
                            if(objUserPriv.getAccessFlag().equals("R"))
                            {
                                objUserForm.setOrderConfirmRead("Y");
                                objUserForm.setOrderConfirmPost("N");
                            } else
                            if(objUserPriv.getAccessFlag().equals("W"))
                            {
                                objUserForm.setOrderConfirmRead("N");
                                objUserForm.setOrderConfirmPost("Y");
                            }
                        }
                    } else
                    if(objReference.getDescription().equals("Order Status"))
                    {
                        if(objReference.getRefId().equals(objUserPriv.getModuleDetail().getRefId()) && objUser.getUserId().equals(objUserPriv.getUserDetail().getUserId()))
                        {
                            if(objUserPriv.getAccessFlag().equals("B"))
                            {
                                objUserForm.setOrderStatusRead("Y");
                                objUserForm.setOrderStatusPost("Y");
                            } else
                            if(objUserPriv.getAccessFlag().equals("N"))
                            {
                                objUserForm.setOrderStatusRead("N");
                                objUserForm.setOrderStatusPost("N");
                            } else
                            if(objUserPriv.getAccessFlag().equals("R"))
                            {
                                objUserForm.setOrderStatusRead("Y");
                                objUserForm.setOrderStatusPost("N");
                            } else
                            if(objUserPriv.getAccessFlag().equals("W"))
                            {
                                objUserForm.setOrderStatusRead("N");
                                objUserForm.setOrderStatusPost("Y");
                            }
                        }
                    } else
                    if(objReference.getDescription().equals("Delivery Message"))
                    {
                        if(objReference.getRefId().equals(objUserPriv.getModuleDetail().getRefId()) && objUser.getUserId().equals(objUserPriv.getUserDetail().getUserId()))
                        {
                            if(objUserPriv.getAccessFlag().equals("B"))
                            {
                                objUserForm.setDeliveryMesgRead("Y");
                                objUserForm.setDeliveryMesgPost("Y");
                            } else
                            if(objUserPriv.getAccessFlag().equals("N"))
                            {
                                objUserForm.setDeliveryMesgRead("N");
                                objUserForm.setDeliveryMesgPost("N");
                            } else
                            if(objUserPriv.getAccessFlag().equals("R"))
                            {
                                objUserForm.setDeliveryMesgRead("Y");
                                objUserForm.setDeliveryMesgPost("N");
                            } else
                            if(objUserPriv.getAccessFlag().equals("W"))
                            {
                                objUserForm.setDeliveryMesgRead("N");
                                objUserForm.setDeliveryMesgPost("Y");
                            }
                        }
                    } else
                    if(objReference.getDescription().equals("Goods Receipt"))
                    {
                        if(objReference.getRefId().equals(objUserPriv.getModuleDetail().getRefId()) && objUser.getUserId().equals(objUserPriv.getUserDetail().getUserId()))
                        {
                            if(objUserPriv.getAccessFlag().equals("B"))
                            {
                                objUserForm.setGoodsReceiptRead("Y");
                                objUserForm.setGoodsReceiptPost("Y");
                            } else
                            if(objUserPriv.getAccessFlag().equals("N"))
                            {
                                objUserForm.setGoodsReceiptRead("N");
                                objUserForm.setGoodsReceiptPost("N");
                            } else
                            if(objUserPriv.getAccessFlag().equals("R"))
                            {
                                objUserForm.setGoodsReceiptRead("Y");
                                objUserForm.setGoodsReceiptPost("N");
                            } else
                            if(objUserPriv.getAccessFlag().equals("W"))
                            {
                                objUserForm.setGoodsReceiptRead("N");
                                objUserForm.setGoodsReceiptPost("Y");
                            }
                        }
                    } else
                    if(objReference.getDescription().equals("Usage") && objReference.getRefId().equals(objUserPriv.getModuleDetail().getRefId()) && objUser.getUserId().equals(objUserPriv.getUserDetail().getUserId()))
                    {
                        if(objUserPriv.getAccessFlag().equals("B"))
                        {
                            objUserForm.setUsageRead("Y");
                            objUserForm.setUsagePost("Y");
                        } else
                        if(objUserPriv.getAccessFlag().equals("N"))
                        {
                            objUserForm.setUsageRead("N");
                            objUserForm.setUsagePost("N");
                        } else
                        if(objUserPriv.getAccessFlag().equals("R"))
                        {
                            objUserForm.setUsageRead("Y");
                            objUserForm.setUsagePost("N");
                        } else
                        if(objUserPriv.getAccessFlag().equals("W"))
                        {
                            objUserForm.setUsageRead("N");
                            objUserForm.setUsagePost("Y");
                        }
                    }
                    else
                        if(objReference.getDescription().equals("ARP Title SetUp"))
                        {
                            if(objReference.getRefId().equals(objUserPriv.getModuleDetail().getRefId()) && objUser.getUserId().equals(objUserPriv.getUserDetail().getUserId()))
                            {
                                if(objUserPriv.getAccessFlag().equals("B"))
                                {
                                    objUserForm.setArpTitleRead("Y");
                                    objUserForm.setArpTitlePost("Y");
                                } else
                                if(objUserPriv.getAccessFlag().equals("N"))
                                {
                                    objUserForm.setArpTitleRead("N");
                                    objUserForm.setArpTitlePost("N");
                                } else
                                if(objUserPriv.getAccessFlag().equals("R"))
                                {
                                    objUserForm.setArpTitleRead("Y");
                                    objUserForm.setArpTitlePost("N");
                                } else
                                if(objUserPriv.getAccessFlag().equals("W"))
                                {
                                    objUserForm.setArpTitleRead("N");
                                    objUserForm.setArpTitlePost("Y");
                                }
                            }
                        }else
                            if(objReference.getDescription().equals("Dropship"))
                            {
                                if(objReference.getRefId().equals(objUserPriv.getModuleDetail().getRefId()) && objUser.getUserId().equals(objUserPriv.getUserDetail().getUserId()))
                                {
                                    if(objUserPriv.getAccessFlag().equals("B"))
                                    {
                                        objUserForm.setDropshipinstructionsRead("Y");
                                        objUserForm.setDropshipinstructionsPost("Y");
                                    } else
                                    if(objUserPriv.getAccessFlag().equals("N"))
                                    {
                                        objUserForm.setDropshipinstructionsRead("N");
                                        objUserForm.setDropshipinstructionsPost("N");
                                    } else
                                    if(objUserPriv.getAccessFlag().equals("R"))
                                    {
                                        objUserForm.setDropshipinstructionsRead("Y");
                                        objUserForm.setDropshipinstructionsPost("N");
                                    } else
                                    if(objUserPriv.getAccessFlag().equals("W"))
                                    {
                                        objUserForm.setDropshipinstructionsRead("N");
                                        objUserForm.setDropshipinstructionsPost("Y");
                                    }
                                }
                            }
                }

            }

            objUserForm.setUser(objUser);
            return "display";
        }
        catch(AppException e)
        {
            String errMsg = e.getSErrorDescription();
            request.setAttribute("PIX_ERROR", errMsg);
            return "error";
        }
        catch(Exception e)
        {
            AppException ae = new AppException();
            ae.performErrorAction("9000", "AdminCommand,executeUserDisplay", e);
            String errMsg = ae.getSErrorDescription();
            request.setAttribute("PIX_ERROR", errMsg);
            e.printStackTrace();
            return "error";
        }
    }

    private String executeUserUpdate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        UserForm objUserForm = (UserForm)form;
        AdminDelegate objAdminDelegate = new AdminDelegate();
        User objUser = objUserForm.getUser();
        UserPriv objUserPriv = null;
        UserPriv objUserPrivPrev = null;
        Vector objVector = null;
        Reference objReference = new Reference();
        Vector objPrivilegeCollection = null;
        User newUser = null;
        String err = "Please rectify the following errors :<br>";
        Integer userId = null;
        try
        {
            Calendar cal = Calendar.getInstance();
            java.util.Date today = cal.getTime();
            if("EDITPROFILE".equals(request.getParameter("LOGIN")))
            {
                request.setAttribute("OK_URL", "/home/home.do");
                if((request.getSession().getAttribute("ADMIN_MODULE") != null && request.getSession().getAttribute("LOGIN") != null || "EDITPROFILE".equals(request.getParameter("LOGIN"))) && request.getSession().getAttribute("Password") != null)
                {
                    if(request.getSession().getAttribute("ADMIN_MODULE") != null && request.getSession().getAttribute("LOGIN") != null)
                    {
                        String temp = objAdminDelegate.getPasswordInfo(objUser.getUserId(), objUser.getPassword());
                        request.setAttribute("match", temp);
                        if(temp.equals("N"))
                        {
                            objUser.setPasswordExpiry("N");
                        } else
                        if(temp.equals("Y1"))
                        {
                            request.setAttribute("PasswordExist", err + "<LI>The first 5 characters of the Password are same as previous Password, Please select a different Password.</LI>");
                            if(request.getSession().getAttribute("ADMIN_MODULE") != null && request.getSession().getAttribute("LOGIN") != null)
                            {
                                return "exists";
                            }
                        } else
                        {
                            request.setAttribute("PasswordExist", err + "<LI>Password should not match with last seven passwords.</LI>");
                            if(request.getSession().getAttribute("ADMIN_MODULE") != null && request.getSession().getAttribute("LOGIN") != null)
                            {
                                return "exists";
                            }
                        }
                        request.getSession().removeAttribute("ADMIN_MODULE");
                        request.getSession().removeAttribute("LOGIN");
                        request.getSession().removeAttribute("Password");
                    } else
                    {
                        if(!objUser.getPassword().equals(request.getSession().getAttribute("Password")))
                        {
                            String temp = objAdminDelegate.getPasswordInfo(objUser.getUserId(), objUser.getPassword());
                            request.setAttribute("match", temp);
                            if(temp.equals("N"))
                            {
                                objUser.setPasswordExpiry("N");
                            } else
                            if(temp.equals("Y1"))
                            {
                                request.setAttribute("PasswordExist", err + "<LI>The first 5 characters of the Password are same as previous Password, Please select a different Password.</LI>");
                                return "editprofileexists";
                            } else
                            {
                                request.setAttribute("PasswordExist", err + "<LI>Password should not match with last seven passwords.</LI>");
                                return "editprofileexists";
                            }
                        }
                        request.getSession().removeAttribute("Password");
                    }
                }
            } else
            {
                if(!"EDITPROFILE".equals(request.getParameter("LOGIN")) && request.getParameter("LOGIN") != null)
                {
                    if(!objUser.getPassword().equals(request.getSession().getAttribute("Password")))
                    {
                        String temp = objAdminDelegate.getPasswordInfo(Integer.valueOf(request.getParameter("LOGIN")), objUser.getPassword());
                        request.setAttribute("match", temp);
                        if(temp.equals("N"))
                        {
                            objUser.setPasswordExpiry("N");
                        } 
                        else if(temp.equals("Y1"))
                        {
                            request.setAttribute("PasswordExist", err + "<LI>The first 5 characters of the Password are same as previous Password, Please select a different Password.</LI>");
                            return "userpassword";
                        } 
                        else
                        {
                            request.setAttribute("PasswordExist", err + "<LI>Password should not match with last seven passwords.</LI>");
                            return "userpassword";
                        }
                    }
                    request.getSession().removeAttribute("ADMIN_MODULE");
                    request.getSession().removeAttribute("LOGIN");
                    request.getSession().removeAttribute("Password");
                }
                if(request.getParameter("PAGE_VALUE") != "" && request.getParameter("PAGE_VALUE") != null)
                {
                    String page_value = request.getParameter("PAGE_VALUE");
                    String list_page = "?PAGE_VALUE=" + page_value;
                    if((!PIXUtil.checkNullField(request.getParameter("userFilter"))) & (!PIXUtil.checkNullField(request.getParameter("accountStatusFilter"))) & (!PIXUtil.checkNullField(request.getParameter("accountTypeFilter"))) & (!PIXUtil.checkNullField(request.getParameter("startDateFilter"))) & (!PIXUtil.checkNullField(request.getParameter("endDateFilter"))))
                    {
                        request.setAttribute("OK_URL", "/admin/listuser.do?PAGE_VALUE=" + page_value + "&ADMIN_MODULE=USER");
                    } else
                    {
                        if(request.getParameter("userFilter") != "")
                        {
                            list_page = list_page + "&userFilter=" + request.getParameter("userFilter");
                        }
                        if(request.getParameter("accountStatusFilter") != "")
                        {
                            list_page = list_page + "&accountStatusFilter=" + request.getParameter("accountStatusFilter");
                        }
                        if(request.getParameter("accountTypeFilter") != "")
                        {
                            list_page = list_page + "&accountTypeFilter=" + request.getParameter("accountTypeFilter");
                        }
                        if(request.getParameter("startDateFilter") != "")
                        {
                            list_page = list_page + "&startDateFilter=" + request.getParameter("startDateFilter");
                        }
                        if(request.getParameter("endDateFilter") != "")
                        {
                            list_page = list_page + "&endDateFilter=" + request.getParameter("endDateFilter");
                        }
                        request.setAttribute("OK_URL", "/admin/listuser.do" + list_page + "&ADMIN_MODULE=USER");
                    }
                } else
                {
                    request.setAttribute("OK_URL", "/admin/listuser.do?PAGE_VALUE=1&ADMIN_MODULE=USER");
                }
            }
            if(request.getSession().getAttribute("USER_INFO") != null)
            {
                newUser = (User)request.getSession().getAttribute("USER_INFO");
                userId = newUser.getUserId();
            }
            objVector = new Vector();
            objVector = objAdminDelegate.getUserModuleInfo();
            objPrivilegeCollection = (Vector)objUser.getPrivilegeCollection();
            int vectorSize = objVector != null ? objVector.size() : 0;
            for(int i = 0; i < vectorSize; i++)
            {
                objUserPriv = new UserPriv();
                objReference = (Reference)objVector.get(i);
                if(objReference.getDescription().equals("Onhand Inventory"))
                {
                    for(int j = 0; j < objPrivilegeCollection.size(); j++)
                    {
                        objUserPrivPrev = (UserPriv)objPrivilegeCollection.elementAt(j);
                        if(objUserPrivPrev.getModuleDetail().getDescription().equals("Onhand Inventory"))
                        {
                            objUser.removeFromPrivilegeCollection(objUserPrivPrev);
                        }
                    }

                    if(objUserForm.getInventoryRead().equals("Y") && objUserForm.getInventoryPost().equals("Y"))
                    {
                        objUserPriv.setAccessFlag("B");
                    } else
                    if(objUserForm.getInventoryRead().equals("N") && objUserForm.getInventoryPost().equals("N"))
                    {
                        objUserPriv.setAccessFlag("N");
                    } else
                    if(objUserForm.getInventoryRead().equals("Y"))
                    {
                        objUserPriv.setAccessFlag("R");
                    } else
                    if(objUserForm.getInventoryPost().equals("Y"))
                    {
                        objUserPriv.setAccessFlag("W");
                    }
                    objUserPriv.setModuleDetail(objReference);
                    objUserPriv.setModDateTime(today);
                    objUserPriv.setModifiedBy(userId);
                    objUser.addToPrivilegeCollection(objUserPriv);
                } else
                if(objReference.getDescription().equals("Planning"))
                {
                    for(int j = 0; j < objPrivilegeCollection.size(); j++)
                    {
                        objUserPrivPrev = (UserPriv)objPrivilegeCollection.elementAt(j);
                        if(objUserPrivPrev.getModuleDetail().getDescription().equals("Planning"))
                        {
                            objUser.removeFromPrivilegeCollection(objUserPrivPrev);
                        }
                    }

                    if(objUserForm.getPlanningRead().equals("Y") && objUserForm.getPlanningPost().equals("Y"))
                    {
                        objUserPriv.setAccessFlag("B");
                    } else
                    if(objUserForm.getPlanningRead().equals("N") && objUserForm.getPlanningPost().equals("N"))
                    {
                        objUserPriv.setAccessFlag("N");
                    } else
                    if(objUserForm.getPlanningRead().equals("Y"))
                    {
                        objUserPriv.setAccessFlag("R");
                    } else
                    if(objUserForm.getPlanningPost().equals("Y"))
                    {
                        objUserPriv.setAccessFlag("W");
                    }
                    objUserPriv.setModuleDetail(objReference);
                    objUserPriv.setModDateTime(today);
                    objUserPriv.setModifiedBy(userId);
                    objUser.addToPrivilegeCollection(objUserPriv);
                } else
                if(objReference.getDescription().equals("Book Specification"))
                {
                    for(int j = 0; j < objPrivilegeCollection.size(); j++)
                    {
                        objUserPrivPrev = (UserPriv)objPrivilegeCollection.elementAt(j);
                        if(objUserPrivPrev.getModuleDetail().getDescription().equals("Book Specification"))
                        {
                            objUser.removeFromPrivilegeCollection(objUserPrivPrev);
                        }
                    }

                    if(objUserForm.getBookSpecRead().equals("Y") && objUserForm.getBookSpecPost().equals("Y"))
                    {
                        objUserPriv.setAccessFlag("B");
                    } else
                    if(objUserForm.getBookSpecRead().equals("N") && objUserForm.getBookSpecPost().equals("N"))
                    {
                        objUserPriv.setAccessFlag("N");
                    } else
                    if(objUserForm.getBookSpecRead().equals("Y"))
                    {
                        objUserPriv.setAccessFlag("R");
                    } else
                    if(objUserForm.getBookSpecPost().equals("Y"))
                    {
                        objUserPriv.setAccessFlag("W");
                    }
                    objUserPriv.setModuleDetail(objReference);
                    objUserPriv.setModDateTime(today);
                    objUserPriv.setModifiedBy(userId);
                    objUser.addToPrivilegeCollection(objUserPriv);
                } else
                if(objReference.getDescription().equals("Purchase Orders"))
                {
                    for(int j = 0; j < objPrivilegeCollection.size(); j++)
                    {
                        objUserPrivPrev = (UserPriv)objPrivilegeCollection.elementAt(j);
                        if(objUserPrivPrev.getModuleDetail().getDescription().equals("Purchase Orders"))
                        {
                            objUser.removeFromPrivilegeCollection(objUserPrivPrev);
                        }
                    }

                    if(objUserForm.getPurchaseOrderRead().equals("Y") && objUserForm.getPurchaseOrderPost().equals("Y"))
                    {
                        objUserPriv.setAccessFlag("B");
                    } else
                    if(objUserForm.getPurchaseOrderRead().equals("N") && objUserForm.getPurchaseOrderPost().equals("N"))
                    {
                        objUserPriv.setAccessFlag("N");
                    } else
                    if(objUserForm.getPurchaseOrderRead().equals("Y"))
                    {
                        objUserPriv.setAccessFlag("R");
                    } else
                    if(objUserForm.getPurchaseOrderPost().equals("Y"))
                    {
                        objUserPriv.setAccessFlag("W");
                    }
                    objUserPriv.setModuleDetail(objReference);
                    objUserPriv.setModDateTime(today);
                    objUserPriv.setModifiedBy(userId);
                    objUser.addToPrivilegeCollection(objUserPriv);
                } else
                if(objReference.getDescription().equals("Order Confirmation"))
                {
                    for(int j = 0; j < objPrivilegeCollection.size(); j++)
                    {
                        objUserPrivPrev = (UserPriv)objPrivilegeCollection.elementAt(j);
                        if(objUserPrivPrev.getModuleDetail().getDescription().equals("Order Confirmation"))
                        {
                            objUser.removeFromPrivilegeCollection(objUserPrivPrev);
                        }
                    }

                    if(objUserForm.getOrderConfirmRead().equals("Y") && objUserForm.getOrderConfirmPost().equals("Y"))
                    {
                        objUserPriv.setAccessFlag("B");
                    } else
                    if(objUserForm.getOrderConfirmRead().equals("N") && objUserForm.getOrderConfirmPost().equals("N"))
                    {
                        objUserPriv.setAccessFlag("N");
                    } else
                    if(objUserForm.getOrderConfirmRead().equals("Y"))
                    {
                        objUserPriv.setAccessFlag("R");
                    } else
                    if(objUserForm.getOrderConfirmPost().equals("Y"))
                    {
                        objUserPriv.setAccessFlag("W");
                    }
                    objUserPriv.setModuleDetail(objReference);
                    objUserPriv.setModDateTime(today);
                    objUserPriv.setModifiedBy(userId);
                    objUser.addToPrivilegeCollection(objUserPriv);
                } else
                if(objReference.getDescription().equals("Order Status"))
                {
                    for(int j = 0; j < objPrivilegeCollection.size(); j++)
                    {
                        objUserPrivPrev = (UserPriv)objPrivilegeCollection.elementAt(j);
                        if(objUserPrivPrev.getModuleDetail().getDescription().equals("Order Status"))
                        {
                            objUser.removeFromPrivilegeCollection(objUserPrivPrev);
                        }
                    }

                    if(objUserForm.getOrderStatusRead().equals("Y") && objUserForm.getOrderStatusPost().equals("Y"))
                    {
                        objUserPriv.setAccessFlag("B");
                    } else
                    if(objUserForm.getOrderStatusRead().equals("N") && objUserForm.getOrderStatusPost().equals("N"))
                    {
                        objUserPriv.setAccessFlag("N");
                    } else
                    if(objUserForm.getOrderStatusRead().equals("Y"))
                    {
                        objUserPriv.setAccessFlag("R");
                    } else
                    if(objUserForm.getOrderStatusPost().equals("Y"))
                    {
                        objUserPriv.setAccessFlag("W");
                    }
                    objUserPriv.setModuleDetail(objReference);
                    objUserPriv.setModDateTime(today);
                    objUserPriv.setModifiedBy(userId);
                    objUser.addToPrivilegeCollection(objUserPriv);
                } else
                if(objReference.getDescription().equals("Delivery Message"))
                {
                    for(int j = 0; j < objPrivilegeCollection.size(); j++)
                    {
                        objUserPrivPrev = (UserPriv)objPrivilegeCollection.elementAt(j);
                        if(objUserPrivPrev.getModuleDetail().getDescription().equals("Delivery Message"))
                        {
                            objUser.removeFromPrivilegeCollection(objUserPrivPrev);
                        }
                    }

                    if(objUserForm.getDeliveryMesgRead().equals("Y") && objUserForm.getDeliveryMesgPost().equals("Y"))
                    {
                        objUserPriv.setAccessFlag("B");
                    } else
                    if(objUserForm.getDeliveryMesgRead().equals("N") && objUserForm.getDeliveryMesgPost().equals("N"))
                    {
                        objUserPriv.setAccessFlag("N");
                    } else
                    if(objUserForm.getDeliveryMesgRead().equals("Y"))
                    {
                        objUserPriv.setAccessFlag("R");
                    } else
                    if(objUserForm.getDeliveryMesgPost().equals("Y"))
                    {
                        objUserPriv.setAccessFlag("W");
                    }
                    objUserPriv.setModuleDetail(objReference);
                    objUserPriv.setModDateTime(today);
                    objUserPriv.setModifiedBy(userId);
                    objUser.addToPrivilegeCollection(objUserPriv);
                } else
                if(objReference.getDescription().equals("Goods Receipt"))
                {
                    for(int j = 0; j < objPrivilegeCollection.size(); j++)
                    {
                        objUserPrivPrev = (UserPriv)objPrivilegeCollection.elementAt(j);
                        if(objUserPrivPrev.getModuleDetail().getDescription().equals("Goods Receipt"))
                        {
                            objUser.removeFromPrivilegeCollection(objUserPrivPrev);
                        }
                    }

                    if(objUserForm.getGoodsReceiptRead().equals("Y") && objUserForm.getGoodsReceiptPost().equals("Y"))
                    {
                        objUserPriv.setAccessFlag("B");
                    } else
                    if(objUserForm.getGoodsReceiptRead().equals("N") && objUserForm.getGoodsReceiptPost().equals("N"))
                    {
                        objUserPriv.setAccessFlag("N");
                    } else
                    if(objUserForm.getGoodsReceiptRead().equals("Y"))
                    {
                        objUserPriv.setAccessFlag("R");
                    } else
                    if(objUserForm.getGoodsReceiptPost().equals("Y"))
                    {
                        objUserPriv.setAccessFlag("W");
                    }
                    objUserPriv.setModuleDetail(objReference);
                    objUserPriv.setModDateTime(today);
                    objUserPriv.setModifiedBy(userId);
                    objUser.addToPrivilegeCollection(objUserPriv);
                } else
                if(objReference.getDescription().equals("Usage"))
                {
                    for(int j = 0; j < objPrivilegeCollection.size(); j++)
                    {
                        objUserPrivPrev = (UserPriv)objPrivilegeCollection.elementAt(j);
                        if(objUserPrivPrev.getModuleDetail().getDescription().equals("Usage"))
                        {
                            objUser.removeFromPrivilegeCollection(objUserPrivPrev);
                        }
                    }

                    if(objUserForm.getUsageRead().equals("Y") && objUserForm.getUsagePost().equals("Y"))
                    {
                        objUserPriv.setAccessFlag("B");
                    } else
                    if(objUserForm.getUsageRead().equals("N") && objUserForm.getUsagePost().equals("N"))
                    {
                        objUserPriv.setAccessFlag("N");
                    } else
                    if(objUserForm.getUsageRead().equals("Y"))
                    {
                        objUserPriv.setAccessFlag("R");
                    } else
                    if(objUserForm.getUsagePost().equals("Y"))
                    {
                        objUserPriv.setAccessFlag("W");
                    }
                    objUserPriv.setModuleDetail(objReference);
                    objUserPriv.setModDateTime(today);
                    objUserPriv.setModifiedBy(userId);
                    objUser.addToPrivilegeCollection(objUserPriv);
                }else
                if(objReference.getDescription().equals("ARP Title SetUp"))
                {
                    for(int j = 0; j < objPrivilegeCollection.size(); j++)
                    {
                        objUserPrivPrev = (UserPriv)objPrivilegeCollection.elementAt(j);
                        if(objUserPrivPrev.getModuleDetail().getDescription().equals("ARP Title SetUp"))
                        {
                            objUser.removeFromPrivilegeCollection(objUserPrivPrev);
                        }
                    }

                    if(objUserForm.getArpTitleRead().equals("Y") && objUserForm.getArpTitlePost().equals("Y"))
                    {
                        objUserPriv.setAccessFlag("B");
                    } else
                    if(objUserForm.getArpTitleRead().equals("N") && objUserForm.getArpTitlePost().equals("N"))
                    {
                        objUserPriv.setAccessFlag("N");
                    } else
                    if(objUserForm.getArpTitleRead().equals("Y"))
                    {
                        objUserPriv.setAccessFlag("R");
                    } else
                    if(objUserForm.getArpTitlePost().equals("Y"))
                    {
                        objUserPriv.setAccessFlag("W");
                    }
                    objUserPriv.setModuleDetail(objReference);
                    objUserPriv.setModDateTime(today);
                    objUserPriv.setModifiedBy(userId);
                    objUser.addToPrivilegeCollection(objUserPriv);
                }  
                else
                    if(objReference.getDescription().equals("Dropship"))
                    {
                        for(int j = 0; j < objPrivilegeCollection.size(); j++)
                        {
                            objUserPrivPrev = (UserPriv)objPrivilegeCollection.elementAt(j);
                            if(objUserPrivPrev.getModuleDetail().getDescription().equals("Dropship"))
                            {
                                objUser.removeFromPrivilegeCollection(objUserPrivPrev);
                            }
                        }

                        if(objUserForm.getDropshipinstructionsRead().equals("Y") && objUserForm.getDropshipinstructionsPost().equals("Y"))
                        {
                            objUserPriv.setAccessFlag("B");
                        } else
                        if(objUserForm.getDropshipinstructionsRead().equals("N") && objUserForm.getDropshipinstructionsPost().equals("N"))
                        {
                            objUserPriv.setAccessFlag("N");
                        } else
                        if(objUserForm.getDropshipinstructionsRead().equals("Y"))
                        {
                            objUserPriv.setAccessFlag("R");
                        } else
                        if(objUserForm.getDropshipinstructionsPost().equals("Y"))
                        {
                            objUserPriv.setAccessFlag("W");
                        }
                        objUserPriv.setModuleDetail(objReference);
                        objUserPriv.setModDateTime(today);
                        objUserPriv.setModifiedBy(userId);
                        objUser.addToPrivilegeCollection(objUserPriv);
                    }
                    
                
            }

            objUser.setModDateTime(today);
            objUser.setModifiedBy(userId);
            String login = objAdminDelegate.updateUserDetail(objUser);
            request.getSession().removeAttribute("Password");
            String messageKey = "User " + login + " has been successfully updated.";
            request.setAttribute("SUCCESS_STRING", messageKey);
        }
        catch(AppException e)
        {
            String errMsg = e.getSErrorDescription();
            request.setAttribute("PIX_ERROR", errMsg);
            return "error";
        }
        catch(Exception e)
        {
            AppException ae = new AppException();
            ae.performErrorAction("9000", "AdminCommand,executeUserUpdate", e);
            e.printStackTrace();
            String errMsg = ae.getSErrorDescription();
            request.setAttribute("PIX_ERROR", errMsg);
            return "error";
        }
        return "update";
    }

    private String executeSupplierDelete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        UserForm objUserForm = (UserForm)form;
        User objUser = objUserForm.getUser();
        Vector objPartyCollection = null;
        AdminDelegate objAdminDelegate = new AdminDelegate();
        Party adminParty = null;
        String startDate = null;
        String endDate = null;
        String objOrderBy = "CREATION_DATE_TIME";
        String objSort = "DESC";
        String suppId = "";
        if(request.getParameter("supplierIds") != null)
        {
            suppId = request.getParameter("supplierIds");
        }
        String supp[] = suppId.split(",");
        try
        {
            objPartyCollection = (Vector)objUser.getPartyCollection();
            SupplierForm objSupplierForm = (SupplierForm)request.getSession().getAttribute("supplierForm");
            Vector objSupplierList = null;
            if("USER_ADD".equals(request.getParameter("METHOD")))
            {
                objSupplierList = objSupplierForm.getSupplierListCollection();
                if(objUserForm.getRadioSupplier() != null)
                {
                    for(int s = 0; s < supp.length; s++)
                    {
                        int index = Integer.parseInt(supp[s]);
                        Party objParty = (Party)objPartyCollection.elementAt(index - s);
                        for(int i = 0; i < objPartyCollection.size(); i++)
                        {
                            Party newParty = (Party)objPartyCollection.elementAt(i);
                            if(newParty.getSan().equals(objParty.getSan()))
                            {
                                objPartyCollection.remove(i);
                                objSupplierList.add(objParty);
                            }
                        }

                    }

                }
            } else
            if("USER_EDIT".equals(request.getParameter("METHOD")) && objUserForm.getRadioSupplier() != null)
            {
                for(int s = 0; s < supp.length; s++)
                {
                    int index = Integer.parseInt(supp[s]);
                    Party objParty = (Party)objPartyCollection.elementAt(index - s);
                    int partyCollectionSize = objPartyCollection != null ? objPartyCollection.size() : 0;
                    if(objSupplierForm == null)
                    {
                        adminParty = new Party();
                        adminParty.setPartyType("V");
                        objSupplierList = objAdminDelegate.displayPartiesList(adminParty, startDate, endDate, 0, objOrderBy, objSort);
                        objPartyCollection = (Vector)objUser.getPartyCollection();
                        for(int j = 0; j < partyCollectionSize; j++)
                        {
                            Party newParty = (Party)objPartyCollection.elementAt(j);
                            for(int k = 0; k < objSupplierList.size(); k++)
                            {
                                Party supplierParty = (Party)objSupplierList.get(k);
                                if(newParty.getSan().equals(supplierParty.getSan()))
                                {
                                    objSupplierList.remove(supplierParty);
                                }
                            }

                        }

                    }
                    if(objSupplierList == null)
                    {
                        objSupplierList = objSupplierForm.getSupplierListCollection();
                    }
                    for(int i = 0; i < objPartyCollection.size(); i++)
                    {
                        Party newParty = (Party)objPartyCollection.elementAt(i);
                        if(newParty.getSan().equals(objParty.getSan()))
                        {
                            objUser.removeFromPartyCollection(newParty);
                            objSupplierList.add(objParty);
                        }
                    }

                }

            }
            objUserForm.setRadioSupplier(null);
        }
        catch(AppException e)
        {
            String errMsg = e.getSErrorDescription();
            request.setAttribute("PIX_ERROR", errMsg);
            return "error";
        }
        catch(Exception e)
        {
            AppException ae = new AppException();
            ae.performErrorAction("9000", "AdminCommand,executeSupplierDelete", e);
            String errMsg = ae.getSErrorDescription();
            e.printStackTrace();
            request.setAttribute("PIX_ERROR", errMsg);
            return "error";
        }
        return "forwardfinished";
    }

    private String executePublisherDelete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        UserForm objUserForm = (UserForm)form;
        User objUser = objUserForm.getUser();
        Vector objPartyCollection = null;
        AdminDelegate objAdminDelegate = new AdminDelegate();
        Party adminParty = null;
        String startDate = null;
        String endDate = null;
        String objOrderBy = "CREATION_DATE_TIME";
        String objSort = "DESC";
        String pubId = "";
        if(request.getParameter("publisherIds") != null)
        {
            pubId = request.getParameter("publisherIds");
        }
        String publisher[] = pubId.split(",");
        try
        {
            objPartyCollection = (Vector)objUser.getPartyCollection();
            AddPubUnitForm objPublisherForm = (AddPubUnitForm)request.getSession().getAttribute("addPubUnitForm");
            Vector objPublisherList = null;
            if("USER_ADD".equals(request.getParameter("METHOD")))
            {
                objPublisherList = objPublisherForm.getPubUnitListCollection();
                if(objUserForm.getRadioPublisher() != null)
                {
                    for(int p = 0; p < publisher.length; p++)
                    {
                        int index = Integer.parseInt(publisher[p]);
                        Party objParty = (Party)objPartyCollection.elementAt(index - p);
                        for(int i = 0; i < objPartyCollection.size(); i++)
                        {
                            Party newParty = (Party)objPartyCollection.elementAt(i);
                            if(newParty.getSan().equals(objParty.getSan()))
                            {
                                objPartyCollection.remove(i);
                                objPublisherList.add(objParty);
                            }
                        }

                    }

                }
            } else
            if("USER_EDIT".equals(request.getParameter("METHOD")) && objUserForm.getRadioPublisher() != null)
            {
                for(int p = 0; p < publisher.length; p++)
                {
                    int index = Integer.parseInt(publisher[p]);
                    Party objParty = (Party)objPartyCollection.elementAt(index - p);
                    int partyCollectionSize = objPartyCollection != null ? objPartyCollection.size() : 0;
                    if(objPublisherForm == null)
                    {
                        adminParty = new Party();
                        adminParty.setPartyType("B");
                        objPublisherList = objAdminDelegate.displayPartiesList(adminParty, startDate, endDate, 0, objOrderBy, objSort);
                        objPartyCollection = (Vector)objUser.getPartyCollection();
                        for(int j = 0; j < partyCollectionSize; j++)
                        {
                            Party newParty = (Party)objPartyCollection.elementAt(j);
                            for(int k = 0; k < objPublisherList.size(); k++)
                            {
                                Party publisherParty = (Party)objPublisherList.get(k);
                                if(newParty.getSan().equals(publisherParty.getSan()))
                                {
                                    objPublisherList.remove(publisherParty);
                                }
                            }

                        }

                    }
                    if(objPublisherList == null)
                    {
                        objPublisherList = objPublisherForm.getPubUnitListCollection();
                    }
                    for(int i = 0; i < objPartyCollection.size(); i++)
                    {
                        Party newParty = (Party)objPartyCollection.elementAt(i);
                        if(newParty.getSan().equals(objParty.getSan()))
                        {
                            objUser.removeFromPartyCollection(newParty);
                            objPublisherList.add(objParty);
                        }
                    }

                }

            }
            objUserForm.setRadioPublisher(null);
        }
        catch(AppException e)
        {
            String errMsg = e.getSErrorDescription();
            request.setAttribute("PIX_ERROR", errMsg);
            return "error";
        }
        catch(Exception e)
        {
            AppException ae = new AppException();
            ae.performErrorAction("9000", "AdminCommand,executePublisherDelete", e);
            String errMsg = ae.getSErrorDescription();
            request.setAttribute("PIX_ERROR", errMsg);
            return "error";
        }
        return "forwardfinished";
    }

    private String executeUserDelete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        UserForm objUserForm1 = (UserForm)form;
        AdminDelegate objAdminDelegate = new AdminDelegate();
        Integer userId = null;
        String messageKey = null;
        try
        {
            if(request.getParameter("PAGE_VALUE") != "" && request.getParameter("PAGE_VALUE") != null)
            {
                String page_value = request.getParameter("PAGE_VALUE");
                String list_page = "?PAGE_VALUE=" + page_value;
                if((!PIXUtil.checkNullField(request.getParameter("userFilter"))) & (!PIXUtil.checkNullField(request.getParameter("accountStatusFilter"))) & (!PIXUtil.checkNullField(request.getParameter("accountTypeFilter"))) & (!PIXUtil.checkNullField(request.getParameter("startDateFilter"))) & (!PIXUtil.checkNullField(request.getParameter("endDateFilter"))))
                {
                    request.setAttribute("OK_URL", "/admin/listuser.do?PAGE_VALUE=" + page_value + "&ADMIN_MODULE=USER");
                } else
                {
                    if(request.getParameter("userFilter") != "")
                    {
                        list_page = list_page + "&userFilter=" + request.getParameter("userFilter");
                    }
                    if(request.getParameter("accountStatusFilter") != "")
                    {
                        list_page = list_page + "&accountStatusFilter=" + request.getParameter("accountStatusFilter");
                    }
                    if(request.getParameter("accountTypeFilter") != "")
                    {
                        list_page = list_page + "&accountTypeFilter=" + request.getParameter("accountTypeFilter");
                    }
                    if(request.getParameter("startDateFilter") != "")
                    {
                        list_page = list_page + "&startDateFilter=" + request.getParameter("startDateFilter");
                    }
                    if(request.getParameter("endDateFilter") != "")
                    {
                        list_page = list_page + "&endDateFilter=" + request.getParameter("endDateFilter");
                    }
                    request.setAttribute("OK_URL", "/admin/listuser.do" + list_page + "&ADMIN_MODULE=USER");
                }
            } else
            {
                request.setAttribute("OK_URL", "/admin/listuser.do?PAGE_VALUE=1&ADMIN_MODULE=USER");
            }
            if(objUserForm1.getRadioUser() != null)
            {
                int index = Integer.parseInt(objUserForm1.getRadioUser());
                userId = new Integer(index);
                String login = objAdminDelegate.deleteUser(userId);
                messageKey = "User " + login + " has been successfully disabled.";
                request.setAttribute("SUCCESS_STRING", messageKey);
                return "forwardfinished";
            }
        }
        catch(AppException e)
        {
            String errMsg = e.getSErrorDescription();
            request.setAttribute("PIX_ERROR", errMsg);
            return "error";
        }
        catch(Exception e)
        {
            AppException ae = new AppException();
            ae.performErrorAction("9000", "AdminCommand,executeUserDelete", e);
            String errMsg = ae.getSErrorDescription();
            request.setAttribute("PIX_ERROR", errMsg);
            return "error";
        }
        return "forwardfinished";
    }

    static 
    {
        log = LogFactory.getLog(com.pearson.pix.presentation.admin.command.AdminCommand.class.getName());
    }
}
