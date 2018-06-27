package com.ycjcjy.gene.common.thymeleaf.processor;

import com.ycjcjy.gene.model.SysCaseField;
import com.ycjcjy.gene.service.impl.SysCaseFieldServiceImpl;
import net.onebean.component.SpringUtil;
import net.onebean.util.StringUtils;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.model.*;
import org.thymeleaf.processor.element.AbstractElementTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.templatemode.TemplateMode;

import java.util.List;
/**
 * @author 0neBean
 * 自定义thymeleaf标签 案场选择标签实现 翻写自杨老板
 */
@Component
public class CaseFieldProcessor extends AbstractElementTagProcessor {

    private static final String PREFIX = "field";
    private static final String TAG_NAME = "select";
    private static final int PRECEDENCE = 1000;

    /**
     * 无参构造器
     */
    public CaseFieldProcessor() {
        super(TemplateMode.HTML,PREFIX,TAG_NAME,true,null,false,PRECEDENCE);
    }

    /**
     * 构造器
     * @param dialectPrefix 前缀
     */
    public CaseFieldProcessor(final String dialectPrefix) {
        super(TemplateMode.HTML,dialectPrefix,TAG_NAME,true,null,false,PRECEDENCE);
    }

    @Override
    protected void doProcess(ITemplateContext context, IProcessableElementTag tag, IElementTagStructureHandler structureHandler) {
        /*元素工厂用于创建元素*/
        final IModelFactory modelFactory = context.getModelFactory();

        final ICloseElementTag selectEnd = modelFactory.createCloseElementTag("select");
        IModel select = initSelect(modelFactory,tag);
        select = initOption(modelFactory,tag,select);
        select.add(selectEnd);
        final IModel nodes = modelFactory.createModel();
        nodes.insertModel(nodes.size(),select);
        /*Instruct the engine to replace this entire element with the specified model.*/
        structureHandler.replaceWith(nodes, false);
    }

    /**
     * 初始化select
     * @param modelFactory
     * @param tag
     * @return
     */
    protected IModel initSelect(IModelFactory modelFactory,IProcessableElementTag tag){
        final String name = tag.getAttributeValue("name");
        final String id = tag.getAttributeValue("id");

        final String disabled = tag.getAttributeValue("disabled");
        /*表单input 点击后模态窗口*/
        final IModel select = modelFactory.createModel();
        IOpenElementTag selectStart = modelFactory.createOpenElementTag("select");
        selectStart = modelFactory.setAttribute(selectStart,"data-am-selected","{btnSize: 'sm'}");
        selectStart = modelFactory.setAttribute(selectStart,"name",name);
        selectStart = modelFactory.setAttribute(selectStart,"id",id);
        if (StringUtils.isNotEmpty(disabled) && disabled.equals("disabled")) {
            selectStart = modelFactory.setAttribute(selectStart,"disabled",disabled);
        }
        select.add(selectStart);
        return  select;
    }
    /**
     * 初始化option
     * @param modelFactory
     * @param tag
     * @param select
     * @return
     */
    protected IModel initOption(IModelFactory modelFactory,IProcessableElementTag tag,IModel select){
        final String value = tag.getAttributeValue("value");
        SysCaseFieldServiceImpl sysCaseFieldService = SpringUtil.getBean(SysCaseFieldServiceImpl.class);
        List<SysCaseField> list =  sysCaseFieldService.findAll();

        final IModel option = modelFactory.createModel();
        IOpenElementTag optionStart = modelFactory.createOpenElementTag("option");
        final ICloseElementTag optionEnd = modelFactory.createCloseElementTag("option");
        optionStart =modelFactory.setAttribute(optionStart,"value","  ");
        final IText text = modelFactory.createText("未选择");
        option.add(optionStart);
        option.add(text);
        option.add(optionEnd);
        select.insertModel(select.size(),option);

        for(SysCaseField d:list){
            final IModel optionTemp = modelFactory.createModel();
            IOpenElementTag optionTempStart = modelFactory.createOpenElementTag("option");
            final ICloseElementTag optionTempEnd = modelFactory.createCloseElementTag("option");
            final IText optionTempText = modelFactory.createText(d.getCasefieldname());
            if (StringUtils.isNotEmpty(value)) {
                String [] valueArr = value.split(",");
                for (String s : valueArr) {
                    if(s.equals(String.valueOf(d.getId()))){
                        optionTempStart = modelFactory.setAttribute(optionTempStart,"selected","");
                    }
                }
            }
            optionTempStart = modelFactory.setAttribute(optionTempStart,"value",String.valueOf(d.getId()));
            optionTemp.add(optionTempStart);
            optionTemp.add(optionTempText);
            optionTemp.add(optionTempEnd);
            select.insertModel(select.size(),optionTemp);
        }
        return select;
    }
}
