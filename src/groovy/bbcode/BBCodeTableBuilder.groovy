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
 * {@link groovy.bbcode.BBCodeBuilder BBCodeBuilder} Object. Its purpose
 * is strictly to fascilitate the construction of BBCode Table elements,
 * by way of a {@link #tableMatrix} that, in code, visually defines the
 * structure of the table.
 * </p>
 *
 * <p>
 * The <b><code>BBCodeTableBuilder</code></b> will <i>not</i> validate
 * that the <code>tableMatrix</code> is structurally logical.
 * </p>
 *
 * <h2>Building Tables</h2>
 *
 * <h3>Static Builder</h3>
 *
 * Tables can be built using a <b>Streaming Interface</b> syntax:
 *
 * <code><pre>
 * String table = new BBCodeTableBuilder()
 *     .tr("one", "two", "three")
 *     .tr(1, 2, 3)
 *     .tr("Start Date", new Date(), UUID.randomUUID())
 *     .render()
 *
 * println table
 * </pre></code>
 *
 * <h3>Closure Builder Syntax</h3>
 *
 * <p>
 * Tables can be built using <b>Closures</b>:
 *
 * <code><pre>
 * String table = new BBCodeTableBuilder().table {
 *     tr('Header 1', 'Header 2', 'Header 3')
 *     tr('Body 1-1', 'Body 2-1', 'Body 3-1')
 *     tr('Body 1-2', 'Body 2-2', 'Body 3-2')
 *     tr('Body 1-3', 'Body 2-3', 'Body 3-3')
 * }
 *
 * println table
 * </pre></code>
 *
 * Which produces an output similar to the following, but without
 * newlines and indentation (a single-line <code>String</code>):
 *
 * <code><pre>
 * [table]
 *   [tr]
 *     [td]Header 1[/td]
 *     [td]Header 2[/td]
 *     [td]Header 3[/td]
 *   [/tr]
 *   [tr]
 *     [td]Body 1-1[/td]
 *     [td]Body 2-1[/td]
 *     [td]Body 3-1[/td]
 *   [/tr]
 *   [tr]
 *      [td]Body 1-2[/td]
 *      [td]Body 2-2[/td]
 *      [td]Body 3-2[/td]
 *   [/tr]
 *   [tr]
 *      [td]Body 1-3[/td]
 *      [td]Body 2-3[/td]
 *      [td]Body 3-3[/td]
 *   [/tr]
 * [/table]
 * </pre></code>
 *
 * Which then renders the table:<br />
 *
 * <table border="1">
 *   <tr>
 *     <td>Header 1</td>
 *     <td>Header 2</td>
 *     <td>Header 3</td>
 *   </tr>
 *   <tr>
 *     <td>Body 1-1</td>
 *     <td>Body 2-1</td>
 *     <td>Body 3-1</td>
 *   </tr>
 *   <tr>
 *     <td>Body 1-2</td>
 *     <td>Body 2-2</td>
 *     <td>body 3-2</td>
 *   </tr>
 *   <tr>
 *     <td>Body 1-3</td>
 *     <td>Body 2-3</td>
 *     <td>body 3-3</td>
 *   </tr>
 * </table>
 * </p>
 *
 * <h4><code>BBCodeBuilder</code> inside Closure Syntax</h4>
 *
 * <p>
 * Methods similar to {@link #b(Object)} can be used within the
 * {@link table(Closure)} function:
 *
 * <code><pre>
 * String table = new BBCodeBuilder().table {
 *     tr(b("Header 1"), b("Header 2"), b("Header 3"))
 *     tr("Body 1-1", "Body 2-1", "Body 3-1")
 *     tr("Body 1-2", "Body 2-2", "Body 3-2")
 * }
 *
 * println table
 * </pre></code>
 *
 * Produces:
 * <code><pre>
 * [table]
 *   [tr]
 *     [td][b]Header 1[/b][/td]
 *     [td][b]Header 2[/b][/td]
 *     [td][b]Header 3[/b][/td]
 *   [/tr]
 *   [tr]
 *     [td]Body 1-1[/td]
 *     [td]Body 2-1[/td]
 *     [td]Body 3-1[/td]
 *   [/tr]
 *   [tr]
 *      [td]Body 1-2[/td]
 *      [td]Body 2-2[/td]
 *      [td]Body 3-2[/td]
 *   [/tr]
 * [/table]
 * </pre></code>
 *
 * And renders the table:<br />
 *
 * <table border="1">
 *   <tr>
 *     <th>Header 1</th>
 *     <th>Header 2</th>
 *     <th>Header 3</th>
 *   </tr>
 *   <tr>
 *     <td>Body 1-1</td>
 *     <td>Body 2-1</td>
 *     <td>Body 3-1</td>
 *   </tr>
 *   <tr>
 *     <td>Body 1-2</td>
 *     <td>Body 2-2</td>
 *     <td>body 3-2</td>
 *   </tr>
 * </table>
 * </p>
 *
 * <h4>Nested Tables</h4>
 *
 * <p>
 * The <b><code>BBCodeTableBuilder</code></b> has the ability to
 * generate nested tables (by use of {@link subTable}, which can also
 * include the simple <b><code>BBCodeBuilder</code></b> element methods:
 *
 * <code><pre>
 * String table = new BBCodeBuilder().table {
 *     tr(b("T1 H1"), b("T1 H2"))
 *     tr("T1 H1 B1", "T1 H2 B1")
 *     tr("T1 H1 B2", "T1 H2 B2")
 *     tr(
 *         subTable {
 *             tr(b(red("T2 H1")), b(blue("T2 H2")))
 *             tr("T2 H1 B1", "T2 H2 B1")
 *             tr("T2 H1 B2", "T2 H2 B2")
 *         }
 *     ]
 * }
 * </pre></code>
 *
 * Produces:
 *
 * <code><pre>
 * [table]
 *     [tr]
 *         [td][b]T1 H1[/b]
 *         [td][b]T1 H2[/b]
 *     [/tr]
 *     [tr]
 *         [td]T1 H1 B1[/td]
 *         [td]T1 H2 B1[/td]
 *     [/tr]
 *         [td]T1 H1 B2[/td]
 *         [td]T1 H2 B2[/td]
 *     [tr]
 *         [table]
 *             [tr]
 *                 [td][b][color="red"]T2 H1[/color][/b][/td]
 *                 [td][b][color="blue"]T2 H2[/color][/b][/td]
 *             [/tr]
 *             [tr]
 *                 [td]T2 H1 B1[/td]
 *                 [td]T2 H2 B1[/td]
 *             [/tr]
 *             [tr]
 *                 [td]T2 H1 B2[/td]
 *                 [td]T2 H2 B2[/td]
 *             [/tr]
 *         [/table]
 *     [/tr]
 * [/table]
 * </pre></code>
 *
 * (Table render omitted due to deprecated HTML rendering).
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
     * A <b><code>BBCodeBuilder</code></b> Object to allow the ability
     * to have mnemonics for encapsulating the elements within the
     * <b>Closure Syntax</b> in BBCode as well.
     *
     * @see groovy.bbcode.BBcodeBuilder
     */
    private final bbCodeBuilder = new BBCodeBuilder()

    /**
     * Construct a BBCode Table out of a pre-established
     * {@link #tableMatrix}-like structure.
     *
     * @param   tableMatrix
     *
     * A <code>tableMatrix</code> is a
     * <code>List&lt;List&lt;Object&gt;&gt;</code>, where the inner
     * <code>List&lt;Object&gt;</code> represents Table Row elements and
     * <code>Object</code> represents Table Data Content.
     *
     * @return
     *
     * A <code>[table][tr][td][/td][/tr][/table]</code> structure, as
     * derived by the structure of the <code>tableMatrix</code>.
     */
    public final String table(List tableMatrix) {
        this.tableMatrix = tableMatrix

        render()
    }

    /**
     * Build a table.
     *
     * <p>
     * Sample Usage:
     *
     * <code><pre>
     * String table = new BBCodeTableBuilder().table {
     *     tr('one', 'two', 'three')
     *     tr(
     *         'Side-Header',
     *         subTable {
     *             tr('Vertical', 1)
     *             tr('Headers', 2)
     *         }
     *     )
     *     tr('four', 'five', 'six')
     * }
     * </pre></code>
     * </p>
     *
     * @param   closure
     *
     * A <code>Closure</code> which consists of a series of
     * {@link #tr(Object...)} and possibly {@link #subTable(Closure)}
     * calls.
     *
     * @return
     *
     * A <code>[table][tr][td][/td][/tr][/table]</code> structure, as
     * derived by order and nature of the <code>tr(Object...)</code>
     * and possibly <code>subTable(Closure)</code> calls.
     */
    public final String table(Closure closure) {
        run closure

        render()
    }

    /**
     * Create a Table Row (<code>[tr][/tr]</code>) element.
     *
     * <p>
     * This method can be chained off of a <code>BBCodeBuilder</code>:
     *
     * <code><pre>
     * String table = new BBCodeTableBuilder()
     *     .tableRow('one', 'two', 'three')
     *     .tableRow(1, 2, 3)
     *     .tableRow('Start Date', new Date(), UUID.randomUUID())
     *     .render()
     * </pre></code>
     * </p>
     *
     * @param   tableData
     *
     * An arbitrary number of <code>Object</code>s, which are to exist
     * on a single Table Row.
     *
     * @return
     *
     * The active <b><code>BBCodeTableBuilder</code></b> Object.
     */
    public final BBCodeTableBuilder tableRow(Object... tableData) {
        tableMatrix << tableData
        return this
    }

    /**
     * Create a Table Row (<code>[tr][/tr]</code>) element.
     *
     * <p>
     * This method can be chained off of a <code>BBCodeBuilder</code>:
     *
     * <code><pre>
     * String table = new BBCodeTableBuilder()
     *     .tr('one', 'two', 'three')
     *     .tr(1, 2, 3)
     *     .tr('Start Date', new Date(), UUID.randomUUID())
     *     .render()
     * </pre></code>
     * </p>
     *
     * @param   tableData
     *
     * An arbitrary number of <code>Object</code>s, which are to exist
     * on a single Table Row.
     *
     * @return
     *
     * The active <b><code>BBCodeTableBuilder</code></b> Object.
     */
    public final BBCodeTableBuilder tr(Object... tds) {
        tableRow(tds)
    }

    /**
     * Create a Table element within a Table Data element
     * (<b>used only in Closure Building syntax</b>).
     *
     * <p>
     * Sample Usage:
     *
     * <code><pre>
     * String table = new BBCodeTableBuilder().table {
     *     tr('one', 'two', 'three')
     *     tr(
     *         'Side-Header',
     *         subTable {
     *             tr('Vertical', 1)
     *             tr('Headers', 2)
     *         }
     *     )
     *     tr('four', 'five', 'six')
     * }
     * </pre></code>
     * </p>
     *
     * @param   closure
     *
     * A <code>Closure</code> which consists of a series of
     * {@link #tr(Object...)} and possibly {@link #subTable(Closure)}
     * calls.
     *
     * @return
     *
     * A <code>[table][tr][td][/td][/tr][/table]</code> structure, as
     * derived by order and nature of the <code>tr(Object...)</code>
     * and possibly <code>subTable(Closure)</code> calls.
     */
    public final String subTable(Closure closure) {
        new BBCodeTableBuilder().table(closure)
    }

    /**
     * Render the Table.
     *
     * @return
     *
     * A <code>[table][tr][td][/td][/tr][/table]</code> structure, as
     * derived by the structure of the <code>tableMatrix</code>.
     */
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

    /**
     * @return {@link #render()}
     */
    public String toString() {
        render()
    }

    /**
     * Run the Build Closure.
     *
     * @param   closure
     *
     * The <code>Closure</code> to apply on this
     * <code>BBCodeTableBuilder</code> Object.
     */
    private final void run(Closure closure) {
        Closure runClosure = closure.clone()

        runClosure.delegate = this
        runClosure.resolveStrategy = Closure.DELEGATE_FIRST
        runClosure()
    }

    /**
     * Construct a BBCode <b>Bold</b> element.
     *
     * @param   toBold  The element to make <b>bold</b>.
     *
     * @return The <code>String</code>: <code>[b]foo[/b]</code>.
     */
    public final String bold(Object toBold) {
        bbCodeBuilder.bold(toBold)
    }

    /**
     * Construct a BBCode <b>Bold</b> element.
     *
     * @param   toBold  The element to make <b>bold</b>.
     *
     * @return The <code>String</code>: <code>[b]foo[/b]</code>.
     */
    public final String b(Object toBold) {
        bbCodeBuilder.bold(toBold)
    }

    /**
     * Construct a BBCode <i>Italics</i> element.
     *
     * @param   toItalics   The element to <i>italicize</i>.
     *
     * @return The <code>String</code>: <code>[i]foo[/i]</code>.
     */
    public final String italics(Object toItalics) {
        bbCodeBuilder.italics(toItalics)
    }

    /**
     * Construct a BBCode <i>Italics</i> element.
     *
     * @param   toItalics   The element to <i>italicize</i>.
     *
     * @return The <code>String</code>: <code>[i]foo[/i]</code>.
     */
    public final String ital(Object toItalics) {
        bbCodeBuilder.italics(toItalics)
    }

    /**
     * Construct a BBCode <i>Italics</i> element.
     *
     * @param   toItalics   The element to <i>italicize</i>.
     *
     * @return The <code>String</code>: <code>[i]foo[/i]</code>.
     */
    public final String i(Object toItalics) {
        bbCodeBuilder.italics(toItalics)
    }

    /**
     * Construct a BBCode <strike>Strikethrough</strike> element.
     *
     * @param   toStrikethrough
     *
     * The element to <strike>strikethrough</strike>.
     *
     * @return The <code>String</code>: <code>[s]foo[/s]</code>.
     */
    public final String strikethrough(Object toStrikethrough) {
        bbCodeBuilder.strikethrough(toStrikethrough)
    }

    /**
     * Construct a BBCode <strike>Strikethrough</strike> element.
     *
     * @param   toStrikethrough
     *
     * The element to <strike>strikethrough</strike>.
     *
     * @return The <code>String</code>: <code>[s]foo[/s]</code>.
     */
    public final String strike(Object toStrikethrough) {
        bbCodeBuilder.strikethrough(toStrikethrough)
    }

    /**
     * Construct a BBCode <strike>Strikethrough</strike> element.
     *
     * @param   toStrikethrough
     *
     * The element to <strike>strikethrough</strike>.
     *
     * @return The <code>String</code>: <code>[s]foo[/s]</code>.
     */
    public final String s(Object toStrikethrough) {
        bbCodeBuilder.strikethrough(toStrikethrough)
    }

    /**
     * Construct a BBCode <u>Underline</u> element.
     *
     * @param   toUnderline
     *
     * The element to <u>underline</u>.
     *
     * @return The <code>String</code>: <code>[u]foo[/u]</code>.
     */
    public final String underline(Object toUnderline) {
        bbCodeBuilder.underline(toUnderline)
    }

    /**
     * Construct a BBCode <u>Underline</u> element.
     *
     * @param   toUnderline
     *
     * The element to <u>underline</u>.
     *
     * @return The <code>String</code>: <code>[u]foo[/u]</code>.
     */
    public final String ul(Object toUnderline) {
        bbCodeBuilder.underline(toUnderline)
    }

    /**
     * Construct a BBCode <u>Underline</u> element.
     *
     * @param   toUnderline
     *
     * The element to <u>underline</u>.
     *
     * @return The <code>String</code>: <code>[u]foo[/u]</code>.
     */
    public final String u(Object toUnderline) {
        bbCodeBuilder.underline(toUnderline)
    }

    /**
     * Construct a BBCode URL element.
     *
     * <p>
     * For <code>[url="foo"]bar[/url]</code>, see
     * {@link #url(String, Object)}.
     * </p>
     *
     * @param   url the URL.
     *
     * @return The <code>String</code>: <code>[url]foo[/url]</code>.
     */
    public final String url(String url) {
        bbCodeBuilder.url(url)
    }

    /**
     * Construct a BBCode URL element.
     *
     * <p>
     * For <code>[url]foo[/url]</code>, see
     * {@link #url(String)}.
     * </p>
     *
     * @param   url the URL.
     *
     * @param   renderText  The text to render in place of URL text.
     *
     * @return
     *
     * The <code>String</code>: <code>[url="foo"]bar[/url]</code>.
     */
    public final String url(String url, Object renderText) {
        bbCodeBuilder.url(url, renderText)
    }

    /**
     * Construct a BBCode Color element.
     *
     * @param   color
     *
     * The color to render (as either a name, <code>"red"</code>, or
     * hex value, <code>"#FF0000"</code>.
     *
     * @param   renderText
     *
     * The element to render in the specified <code>color</code>.
     *
     * @return
     *
     * The <code>String</code>:
     * <code>[color="red"]bar[/color]</code>.
     */
    public final String color(String color, Object renderText) {
        bbCodeBuilder.color(color, renderText)
    }

    /**
     * Produce text in <b>Red</b>.
     *
     * @param   renderText
     *
     * The element to render as red text.
     *
     * @return
     *
     * The <code>String</code>:
     * <code>[color="red"]foo[/color]</code>.
     */
    public final String red(Object renderText) {
        bbCodeBuilder.red(renderText)
    }

    /**
     * Produce text in <b>Orange</b>.
     *
     * @param   renderText
     *
     * The element to render as orange text.
     *
     * @return
     *
     * The <code>String</code>:
     * <code>[color="orange"]foo[/color]</code>.
     */
    public final String orange(Object renderText) {
        bbCodeBuilder.orange(renderText)
    }

    /**
     * Produce text in <b>Yellow</b>.
     *
     * @param   renderText
     *
     * The element to render as yellow text.
     *
     * @return
     *
     * The <code>String</code>:
     * <code>[color="yellow"]foo[/color]</code>.
     */
    public final String yellow(Object renderText) {
        bbCodeBuilder.yellow(renderText)
    }

   /**
     * Produce text in <b>Green</b>.
     *
     * @param   renderText
     *
     * The element to render as green text.
     *
     * @return
     *
     * The <code>String</code>:
     * <code>[color="green"]foo[/color]</code>.
     */
    public final String green(Object renderText) {
        bbCodeBuilder.green(renderText)
    }

    /**
     * Produce text in <b>Blue</b>.
     *
     * @param   renderText
     *
     * The element to render as blue text.
     *
     * @return
     *
     * The <code>String</code>:
     * <code>[color="blue"]foo[/color]</code>.
     */
    public final String blue(Object renderText) {
        bbCodeBuilder.blue(renderText)
    }

    /**
     * Produce text in <b>Purple</b>.
     *
     * @param   renderText
     *
     * The element to render as purple text.
     *
     * @return
     *
     * The <code>String</code>:
     * <code>[color="purple"]foo[/color]</code>.
     */
    public final String purple(Object renderText) {
        bbCodeBuilder.purple(renderText)
    }

    /**
     * Produce text in <b>Black</b>.
     *
     * @param   renderText
     *
     * The element to render as black text.
     *
     * @return
     *
     * The <code>String</code>:
     * <code>[color="black"]foo[/color]</code>.
     */
    public final String black(Object renderText) {
        bbCodeBuilder.black(renderText)
    }

    /**
     * Produce text in <b>White</b>.
     *
     * @param   renderText
     *
     * The element to render as white text.
     *
     * @return
     *
     * The <code>String</code>:
     * <code>[color="white"]foo[/color]</code>.
     */
    public final String white(Object renderText) {
        bbCodeBuilder.white(renderText)
    }

    /**
     * Construct BBCode to align text to the <b>center</b>.
     *
     * @param   renderText
     *
     * The element to render as center-aligned text.
     *
     * @return
     *
     * The <code>String</code>:
     * <code>[center]foo[/center]</code>.
     */
    public final String center(Object renderText) {
        bbcodeBuilder.center(renderText)
    }

    /**
     * Construct BBCode to change the text size.
     *
     * @param   size
     *
     * The font-point size of the <code>renderText</code>.
     *
     * @param   renderText
     *
     * The element to render at the specified <code>size</code>
     *
     * @return
     *
     * Given the <code>size</code> is 10, the <code>String</code>:
     * <code>[size=10]foo[/size].
     */
    public final String size(int size, Object renderText) {
        bbcodeBuilder.size(size, renderText)
    }

    /**
     * Construct BBCode to format text to a <b>Block-Quote</b>.
     *
     * @param   renderText
     *
     * The element to render as a block-quote.
     *
     * @return
     *
     * The <code>String</code>:
     * <code>[quote]foo[/quote]</code>.
     */
    public final String quote(Object renderText) {
        bbcodeBuilder.quote(renderText)
    }

    /**
     * Construct BBCode to format text to <b>Code</b> font.
     *
     * @param   renderText
     *
     * The element to render as code-formatted text.
     *
     * @return
     *
     * The <code>String</code>:
     * <code>[code]foo[/code]</code>.
     */
    public final String code(Object renderText) {
        bbcodeBuilder.code(renderText)
    }

    /**
     * Construct BBCode to format elements into a <b>List</b>.
     *
     * @param   listEntries
     *
     * The list entries, to be bulleted as a List.
     *
     * @return
     *
     * The <code>String</code>:
     * <code>[list][*]foo[*]bar[*]baz[/list]</code>
     */
    public final String list(Object... listEntries) {
        bbcodeBuilder.list(listEntries)
    }

    /**
     * Construct a custom BBCode Tag.
     *
     * @param   label
     *
     * The label of the tag.
     *
     * @param   content
     *
     * The content of the tag.
     *
     * @return
     *
     * Given the <code>label</code> as <b><code>foo</code></b> the
     * result is the tag <code>[foo]content[/foo]</code>.
     */
    public final String tag(String label, Object content) {
        bbcodeBuilder.tag(label, content)
    }

    /**
     * Construct a custom BBCode Tag.
     *
     * @param   label
     *
     * The label of the tag.
     *
     * @param   attribute
     *
     * An attribute to assign with the tag.
     *
     * @param   content
     *
     * The content of the tag.
     *
     * @return
     *
     * Given the <code>label</code> as <b><code>foo</code></b> and the
     * <code>attribute</code> as <b><code>bar</code></b>, the result is
     * the tag <code>[foo=bar]content[/foo]</code>.
     */
    public final String tag(String label, Object attribute, Object content) {
        bbcodeBuilder.tag(label, attribute, content)
    }

    /**
     * Build a complex <code>String</code> incorperating multiple tags.
     *
     * <p>
     * Sample:
     *
     * <code><pre>
     * new BBCodeBuilder().build {
     *     "This is a ${b(code('String))} built using ${code(size(5, "build"))}"
     * }
     * </pre></code>
     * </p>
     *
     * @param   closure
     *
     * The Closure to use, which should return a <code>String</code>.
     *
     * @return
     *
     * A <code>String</code> with the BBCodeBuilder syntaxes replaced
     * with BBCode.
     */
    public final String build(Closure closure) {
        bbcodeBuilder.build(closure)
    }
}
