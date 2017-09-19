package com.har.unmanned.mfront.api;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.web.util.HtmlUtils;
import org.springframework.web.util.JavaScriptUtils;

import java.beans.PropertyEditorSupport;

/**
 * 字符串转义Editor
 *
 * @author tanzeng
 */
public class StringEscapeEditor extends PropertyEditorSupport {

    private boolean escapeHTML;

    private boolean escapeJavaScript;

    private boolean escapeSQL;

    public StringEscapeEditor() {
        super();
    }

    public StringEscapeEditor(boolean escapeHTML, boolean escapeJavaScript,
                              boolean escapeSQL) {

        super();

        this.escapeHTML = escapeHTML;

        this.escapeJavaScript = escapeJavaScript;

        this.escapeSQL = escapeSQL;

    }

    @Override
    public void setAsText(String text) {

        if (text == null) {

            setValue(null);

        } else {

            String value = text;

            if (escapeHTML) {
                value = HtmlUtils.htmlEscape(value);
            }

            if (escapeJavaScript) {
                value = JavaScriptUtils.javaScriptEscape(value);
            }

            if (escapeSQL) {
                value = StringEscapeUtils.escapeSql(value);
            }

            setValue(value);
        }
    }

    @Override
    public String getAsText() {
        Object value = getValue();
        return value != null ? value.toString() : "";
    }
}
