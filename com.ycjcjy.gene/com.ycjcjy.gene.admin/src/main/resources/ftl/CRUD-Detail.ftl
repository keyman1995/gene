<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org" xmlns:sec="http://www.thymeleaf.org/extras/spring-security">
<head th:replace="/public/head :: onLoadHead(${description}管理)">
</head>
<body ddata-type="widgets">
<div class="am-g tpl-g">
    <!--引用通用样式-->
    <css th:replace="/public/css :: onLoadCSS"></css>
    <!--引用通用js-->
    <js th:replace="/public/js :: onLoadJS"></js>
    <!-- 模态提示组件 -->
    <div th:include="/public/tips :: Tips"></div>
    <!-- 内容区域 -->
    <div class="tpl-content-wrapper none-margin">
        <div class="row-content am-cf">
            <div class="row">
                <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
                    <div class="row">
                        <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
                            <div class="widget am-cf">
                                <div class="widget-head am-cf">
                                    <th:block th:include="/public/breadCrumbs :: breadCrumbsTempl"/>
                                </div>
                                <div class="widget-body am-fr">

                                    <form class="am-form tpl-form-border-form tpl-form-border-br" id="DataFrom">
                                                <input type="hidden" name="id" th:value="${r"${entity.id}"}">

                                    <#if fieldArr?exists>
                                        <#list fieldArr as item>
                                            <#if item.column_name != 'id'>
                                            <#if item.page_type == 'input_text'>
                                                <div class="am-form-group">
                                                    <label for="${item.column_name}" class="am-u-sm-3 am-form-label">${item.annotation} <span class="tpl-form-line-small-title">Text</span></label>
                                                    <div class="am-u-sm-9">
                                                        <input type="text" class="tpl-form-input" name="${item.column_name}" id="${item.column_name}" placeholder="请输入${item.annotation}" th:disabled="${r"${view}"}" th:value="${'$'}{entity.${item.column_name}}">
                                                        <small th:unless="${r"${view}"}"><#if (item.page_description??)>${item.page_description}<#else>${item.annotation}</#if></small>
                                                    </div>
                                                </div>
                                            </#if>
                                            <#if item.page_type == 'input_password'>
                                                <div class="am-form-group">
                                                    <label for="${item.column_name}" class="am-u-sm-3 am-form-label">${item.annotation} <span class="tpl-form-line-small-title">Password</span></label>
                                                    <div class="am-u-sm-9">
                                                        <input type="password" class="tpl-form-input" name="${item.column_name}" id="${item.column_name}" placeholder="请输入${item.annotation}" th:disabled="${r"${view}"}" th:value="${'$'}{entity.${item.column_name}}">
                                                        <small th:unless="${r"${view}"}"><#if (item.page_description??)>${item.page_description}<#else>${item.annotation}</#if></small>
                                                    </div>
                                                </div>
                                            </#if>
                                            <#if item.page_type == 'input_number'>
                                                <div class="am-form-group">
                                                    <label for="${item.column_name}" class="am-u-sm-3 am-form-label">${item.annotation} <span class="tpl-form-line-small-title">Number</span></label>
                                                    <div class="am-u-sm-9">
                                                        <input type="number" class="tpl-form-input" name="${item.column_name}" id="${item.column_name}" placeholder="请输入${item.annotation}" th:disabled="${r"${view}"}" th:value="${'$'}{entity.${item.column_name}}">
                                                        <small th:unless="${r"${view}"}"><#if (item.page_description??)>${item.page_description}<#else>${item.annotation}</#if></small>
                                                    </div>
                                                </div>
                                            </#if>
                                            <#if item.page_type == 'input_email'>
                                                <div class="am-form-group">
                                                    <label for="${item.column_name}" class="am-u-sm-3 am-form-label">${item.annotation} <span class="tpl-form-line-small-title">Email</span></label>
                                                    <div class="am-u-sm-9">
                                                        <input type="email" class="tpl-form-input" name="${item.column_name}" id="${item.column_name}" placeholder="请输入${item.annotation}" th:disabled="${r"${view}"}" th:value="${'$'}{entity.${item.column_name}}">
                                                        <small th:unless="${r"${view}"}"><#if (item.page_description??)>${item.page_description}<#else>${item.annotation}</#if></small>
                                                    </div>
                                                </div>
                                            </#if>
                                            <#if item.page_type == 'input_switch'>
                                                <div class="am-form-group">
                                                    <label for="${item.column_name}" class="am-u-sm-3 am-form-label">${item.annotation} <span class="tpl-form-line-small-title">Switch</span></label>
                                                    <div class="am-u-sm-9">
                                                        <div class="tpl-switch">
                                                            <input type="hidden" class="hide tpl-switch-hider" value=""/>
                                                            <input type="checkbox" class="ios-switch bigswitch tpl-switch-btn" th:checked="${'$'}{entity.${item.column_name} eq 1}" th:disabled="${r"${view}"}" name="${item.column_name}" id="${item.column_name}"/>
                                                            <div class="tpl-switch-btn-view">
                                                                <div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </#if>
                                            <#if item.page_type == 'input_dic'>
                                                <div class="am-form-group">
                                                    <label for="${item.column_name}" class="am-u-sm-3 am-form-label">${item.annotation} <span class="tpl-form-line-small-title">SelectBox</span></label>
                                                    <div class="am-u-sm-9">
                                                        <dic:code name="${item.column_name}" id="${item.column_name}" code="SF" th:attr="value=${'$'}{entity.${item.column_name}},disabled=${r"${view}"}"/>
                                                    </div>
                                                </div>
                                            </#if>
                                            <#if item.page_type == 'input_textarea'>
                                                <div class="am-form-group">
                                                    <label for="${item.column_name}" class="am-u-sm-3 am-form-label">${item.annotation} <span class="tpl-form-line-small-title">Text</span></label>
                                                    <div class="am-u-sm-9">
                                                        <textarea class="" rows="10" id="${item.column_name}" name="${item.column_name}" placeholder="请输入${item.annotation}"  th:disabled="${r"${view}"}" th:text="${'$'}{entity.${item.column_name}}"></textarea>
                                                        <small th:unless="${r"${view}"}"><#if (item.page_description??)>${item.page_description}<#else>${item.annotation}</#if></small>
                                                    </div>
                                                </div>
                                            </#if>
                                            <#if item.page_type == 'input_menu_tree'>
                                                <div class="am-form-group">
                                                    <label for="${item.column_name}" class="am-u-sm-3 am-form-label">${item.annotation} <span class="tpl-form-line-small-title">Tree</span></label>
                                                    <div class="am-u-sm-9">
                                                        <input type="hidden" name="${item.column_name}" id="${item.column_name}">
                                                        <tree:menu th:attr="businessIputId='${item.column_name}'"/>
                                                        <small th:unless="${r"${view}"}"><#if (item.page_description??)>${item.page_description}<#else>${item.annotation}</#if></small>
                                                    </div>
                                                </div>
                                            </#if>
                                            <#if item.page_type == 'input_org_tree'>
                                                <div class="am-form-group">
                                                    <label for="${item.column_name}" class="am-u-sm-3 am-form-label">${item.annotation} <span class="tpl-form-line-small-title">Tree</span></label>
                                                    <div class="am-u-sm-9">
                                                        <input type="hidden" name="${item.column_name}" id="${item.column_name}">
                                                        <tree:org th:attr="businessIputId='${item.column_name}'"/>
                                                        <small th:unless="${r"${view}"}"><#if (item.page_description??)>${item.page_description}<#else>${item.annotation}</#if></small>
                                                    </div>
                                                </div>
                                            </#if>
                                            <#if item.page_type == 'input_org_user_tree'>
                                                <div class="am-form-group">
                                                    <label for="${item.column_name}" class="am-u-sm-3 am-form-label">${item.annotation} <span class="tpl-form-line-small-title">Tree</span></label>
                                                    <div class="am-u-sm-9">
                                                        <input type="hidden" name="${item.column_name}" id="${item.column_name}">
                                                        <tree:org-user th:attr="businessIputId='${item.column_name}'"/>
                                                        <small th:unless="${r"${view}"}"><#if (item.page_description??)>${item.page_description}<#else>${item.annotation}</#if></small>
                                                    </div>
                                                </div>
                                            </#if>
                                            <#if item.page_type == 'input_date_picker'>
                                                <div class="am-form-group">
                                                    <label for="${item.column_name}" class="am-u-sm-3 am-form-label">${item.annotation} <span class="tpl-form-line-small-title">Data</span></label>
                                                    <div class="am-u-sm-9">
                                                        <input type="text" class="onebean-data-picker-data" id="${item.column_name}" name="${item.column_name}" placeholder="请选择${item.annotation}">
                                                        <small th:unless="${r"${view}"}"><#if (item.page_description??)>${item.page_description}<#else>${item.annotation}</#if></small>
                                                    </div>
                                                </div>
                                            </#if>
                                            <#if item.page_type == 'input_time_picker'>
                                                <div class="am-form-group">
                                                    <label for="${item.column_name}" class="am-u-sm-3 am-form-label">时间选择控件 <span class="tpl-form-line-small-title">Data</span></label>
                                                    <div class="am-u-sm-9">
                                                        <input type="text" class="onebean-data-picker-time" id="${item.column_name}" name="${item.column_name}" placeholder="请选择${item.annotation}">
                                                        <small th:unless="${r"${view}"}"><#if (item.page_description??)>${item.page_description}<#else>${item.annotation}</#if></small>
                                                    </div>
                                                </div>
                                            </#if>
                                            </#if>
                                        </#list>
                                    </#if>
                                        <div class="am-form-group">
                                            <div class="am-u-sm-9 am-u-sm-push-3">
                                                <th:block sec:authorize="hasPermission('$everyone','${prem_name}_SAVE')">
                                                    <button type="submit" class="am-btn am-btn-primary tpl-btn-bg-color-success" th:unless="${r"${view}"}">提交</button>
                                                </th:block>

                                                <th:block sec:authorize="hasPermission('$everyone','${prem_name}_SAVE')">
                                                    <button type="button" class="am-btn am-btn-warning" th:onclick="'routingPage(\'/${mapping}/edit/'+${r"${entity.id}"}+'\')',\'编辑\'" th:if="${r"${view}"}">编辑</button>
                                                </th:block>
                                                <button type="button" class="am-btn am-btn-danger" onClick="routingPage('/${mapping}/preview/')">返回</button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<script th:inline="javascript">
    $(function () {
        validateFrom();
    });

    /**
     * 校验登录表单
     * @returns void
     */
    function validateFrom(){
        $("#DataFrom").validate({
            rules: {
        <#if fieldArr?exists>
        <#list fieldArr as item>
        <#switch item.page_type>
            <#case "input_org_user_tree">
            orgUserTree:{
                <#break>
            <#case "input_org_tree">
            orgTree:{
                <#break>
            <#case "input_menu_tree">
            menuTree:{
                <#break>
            <#default>
            ${item.column_name}:{
        </#switch>
            <#if item.validateArr?exists>
            <#list item.validateArr as v>
                ${v}:<#switch v><#case "minlength">5<#break><#case "maxlength">5<#break><#default>true</#switch><#if (v_index!=item.validateArr?size-1)>,</#if>
            </#list>
            </#if>
            }<#if (item_index!=fieldArr?size-1)>,</#if>
        </#list>
        </#if>

            },
            submitHandler: function(form) { //验证成功时调用
                var param = $('#DataFrom').serializeJson();
                var url = "/${mapping}/save";
                var completeHandler = function (data) {
                    routingPage('/${mapping}/preview/','${description}管理');
                };
                doPost(url,param,completeHandler);
            }
        });
    }

</script>
</body>
</html>