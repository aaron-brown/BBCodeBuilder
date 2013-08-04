/*
 * Copyright 2013 Aaron Brown
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
package groovy.bbcode

import groovy.bbcode.BBCodeBuilder

/**
 * A builder for creating <b>BBCode</b> Table elements.
 *
 * <p>
 * This builder is an extension (in the functionality sense) of the
 * {@link #groovy.bbcode.BBCodeBuilder} Object. Its purpose is strictly
 * to fascilitate the construction of BBCode Table elements, by way
 * of a {@link #tableMatrix} that, in code, visually defines the
 * structure of the table.
 * </p>
 *
 * <p>
 * The <b><code>BBCodeTableBuilder</code></b> will <i>not</i> validate
 * that the <code>tableMatrix</code> is structurally logical.
 * </p>
 *
 * @author Aaron Brown
 */
class BBCodeTableBuilder {

    /**
     * A nested <code>List</code> structure that visually represents
     * a Table in code.
     */
    List tableMatrix = []

    /**
     * <b><code>BBCodeBuilder</code></b> Object to allow the ability to
     * have mnemonics for encapsulating the elements within the table
     * in BBCode as well.
     */
    private final bbCodeBuilder = new BBCodeBuilder()

    public final String table(List tableMatrix) {
        this.tableMatrix = tableMatrix

        render()
    }

    public final String table(Closure closure) {
        run closure

        render()
    }

    public final BBCodeTableBuilder tableRow(Object... tableData) {
        tableMatrix << tableData
        return this
    }

    public final BBCodeTableBuilder tr(Object... tds) {
        tableRow(tds)
    }

    public final String subTable(Closure closure) {
        new BBCodeTableBuilder().table(closure)
    }

    public final String render() {
        StringBuffer table = new StringBuffer("[table][/table]")

        tableMatrix.each { tr ->
            table.insert((table.size() - 8), "[tr][/tr]")
            tr.each { td ->
                table.insert((table.size() - 13), "[td]${td}[/td]")
            }
        }

        return table
    }

    public String toString() {
        render()
    }

    private final void run(Closure closure) {
        Closure runClosure = closure.clone()

        runClosure.delegate = this
        runClosure.resolveStrategy = Closure.DELEGATE_FIRST
        runClosure()
    }

    public final String bold(Object toBold) {
        bbCodeBuilder.bold(toBold)
    }

    public final String b(Object toBold) {
        bbCodeBuilder.bold(toBold)
    }

    public final String italics(Object toItalics) {
        bbCodeBuilder.italics(toItalics)
    }

    public final String ital(Object toItalics) {
        bbCodeBuilder.italics(toItalics)
    }

    public final String i(Object toItalics) {
        bbCodeBuilder.italics(toItalics)
    }

    public final String strikethrough(Object toStrikethrough) {
        bbCodeBuilder.strikethrough(toStrikethrough)
    }

    public final String strike(Object toStrikethrough) {
        bbCodeBuilder.strikethrough(toStrikethrough)
    }

    public final String s(Object toStrikethrough) {
        bbCodeBuilder.strikethrough(toStrikethrough)
    }

    public final String underline(Object toUnderline) {
        bbCodeBuilder.underline(toUnderline)
    }

    public final String ul(Object toUnderline) {
        bbCodeBuilder.underline(toUnderline)
    }

    public final String u(Object toUnderline) {
        bbCodeBuilder.underline(toUnderline)
    }

    public final String url(String url) {
        bbCodeBuilder.url(url)
    }

    public final String url(String url, Object renderText) {
        bbCodeBuilder.url(url, renderText)
    }

    public final String color(String color, Object renderText) {
        bbCodeBuilder.color(color, renderText)
    }

    public final String red(Object renderText) {
        bbCodeBuilder.red(renderText)
    }

    public final String orange(Object renderText) {
        bbCodeBuilder.orange(renderText)
    }

    public final String yellow(Object renderText) {
        bbCodeBuilder.yellow(renderText)
    }

    public final String green(Object renderText) {
        bbCodeBuilder.green(renderText)
    }

    public final String blue(Object renderText) {
        bbCodeBuilder.blue(renderText)
    }

    public final String purple(Object renderText) {
        bbCodeBuilder.purple(renderText)
    }

    public final String black(Object renderText) {
        bbCodeBuilder.black(renderText)
    }

    public final String white(Object renderText) {
        bbCodeBuilder.white(renderText)
    }
}
