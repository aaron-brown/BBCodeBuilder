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

import groovy.bbcode.BBCodeTableBuilder

/**
 * A builder for creating <b>BBCode</b> elements.
 *
 * <p>
 * This builder fascilitates the construction of basic BBCode elements
 * to be rendered by BBCode renderers.
 * <p>
 *
 * <h2>Basic Elements</h2>
 *
 * <p>
 * For most BBCode elements, the <code>BBCodeBuilder</code> has a
 * mnemonic which matches the element's BBCode Tag. For example:
 *
 * <code><pre>
 * println new BBCodeBuilder().b("foo")
 * </pre></code>
 *
 * Will render:
 *
 * <code><pre>
 * [b]foo[/b]
 * </pre></code>
 * </p>
 *
 * <h2>Building Tables</h2>
 *
 * <p>
 * The <i>true</i> builder syntax is invoked by the
 * {@link #table(Closure)} method:
 *
 * <code><pre>
 * String table = new BBCodeBuilder().table {
 *     tableMatrix = [
 *         ["Header 1", "Header 2", "Header 3"],
 *         ["Body 1-1", "Body 2-1", "Body 3-1"],
 *         ["Body 1-2", "Body 2-2", "Body 3-2"]
 *     ]
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
 * </table>
 * </p>
 *
 * <p>
 * Methods similar to {@link #b(Object)} can be used within the
 * <b><code>table(Closure)</code></b> function:
 *
 * <code><pre>
 * String table = new BBCodeBuilder().table {
 *     tableMatrix = [
 *         [b("Header 1"), b("Header 2"), b("Header 3")],
 *         ["Body 1-1", "Body 2-1", "Body 3-1"],
 *         ["Body 1-2", "Body 2-2", "Body 3-2"]
 *     ]
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
 * <h3>Nested Tables</h3>
 *
 * <p>
 * The <code>BBCodeBuilder</code> has the ability to generate nested
 * tables, which can also include the simple BBCode element methods:
 *
 * <code><pre>
 * String table = new BBCodeBuilder().table {
 *     tableMatrix = [
 *         [b("T1 H1"), b("T1 H2")],
 *         ["T1 H1 B1", "T1 H2 B1"],
 *         ["T1 H1 B2", "T1 H2 B2"],
 *         [
 *             table {
 *                 tableMatrix = [
 *                     [b(red("T2 H1")), b(blue("T2 H2"))],
 *                     ["T2 H1 B1", "T2 H2 B1"],
 *                     ["T2 H1 B2", "T2 H2 B2"]
 *                 ]
 *             }
 *         ]
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
class BBCodeBuilder {

    /**
     * Construct a BBCode <b>Bold</b> element.
     *
     * @param   toBold  The element to make <b>bold</b>.
     *
     * @return The <code>String</code>: <code>[b]foo[/b]</code>.
     */
    public final String bold(Object toBold) {
        return("[b]${toBold}[/b]")
    }

    /**
     * Construct a BBCode <b>Bold</b> element.
     *
     * @param   toBold  The element to make <b>bold</b>.
     *
     * @return The <code>String</code>: <code>[b]foo[/b]</code>.
     */
    public final String b(Object toBold) {
        bold(toBold)
    }

    /**
     * Construct a BBCode <i>Italics</i> element.
     *
     * @param   toItalics   The element to <i>italicize</i>.
     *
     * @return The <code>String</code>: <code>[i]foo[/i]</code>.
     */
    public final String italics(Object toItalics) {
        return("[i]${toItalics}[/i]")
    }

    /**
     * Construct a BBCode <i>Italics</i> element.
     *
     * @param   toItalics   The element to <i>italicize</i>.
     *
     * @return The <code>String</code>: <code>[i]foo[/i]</code>.
     */
    public final String ital(Object toItalics) {
        italics(toItalics)
    }

    /**
     * Construct a BBCode <i>Italics</i> element.
     *
     * @param   toItalics   The element to <i>italicize</i>.
     *
     * @return The <code>String</code>: <code>[i]foo[/i]</code>.
     */
    public final String i(Object toItalics) {
        italics(toItalics)
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
        return("[s]${toStrikethrough}[/s]")
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
        strikethrough(toStrikethrough)
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
        strikethrough(toStrikethrough)
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
        return("[u]${toUnderline}[/u]")
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
        underline(toUnderline)
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
        underline(toUnderline)
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
        return("[url]${url}[/url]")
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
        return("[url=\"${url}\"]${renderText}[/url]")
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
        return("[color=\"${color}\"]${renderText}[/color]")
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
        color('red', renderText)
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
        color('orange', renderText)
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
        color('yellow', renderText)
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
        color('green', renderText)
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
        color('blue', renderText)
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
        color('purple', renderText)
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
        color('black', renderText)
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
        color('white', renderText)
    }

    /**
     * Construct a BBCode Table.
     *
     * <p>
     * The <code>tableMatrix</code> is a matrix-like construction of
     * the table using a series of <code>List</code>s within the
     * <code>tableMatrix</code> list.
     * </p>
     *
     * <p>
     * The outer <code>List</code> represents the
     * <code>[table][/table]</code> structure, like so:
     *
     * <code><pre>
     * List tableMatrix = [
     *     // [table][/table]
     * ]
     * </pre></code>
     *
     * The outer list consists of a series of inner lists, each one
     * representing a <code>[tr][/tr]<code> element:
     *
     * <code><pre>
     * List tableMatrix = [
     *     // [table][/table]
     *     [
     *         // [tr][/tr]
     *     ],
     *     [
     *         // [tr][/tr]
     *     ],
     *     [
     *         // [tr][/tr]
     *     ]
     * ]
     * </pre></code>
     *
     * The contents of each inner <code>List</code> is converted to
     * a <code>String</code>, and constitues the <code>[td][/td]</code>
     * elements:
     *
     * <code><pre>
     * List tableMatrix = [
     *     // [table][/table]
     *
     *     [
     *         // [tr][/tr]
     *
     *         ["a"], // [td]a[/td]
     *         ["b"], // [td]b[/td]
     *         ["c"], // [td]c[/td]
     *     ],
     *     [
     *         // [tr][/tr]
     *
     *         [1], // [td]1[/td]
     *         [2], // [td]2[/td]
     *         [3], // [td]3[/td]
     *     ],
     *     [
     *         // [tr][/tr]
     *
     *         ["morning"], // [td]morning[/td]
     *         ["noon"],    // [td]noon[/td]
     *         ["night"],   // [td]night[/td]
     *     ]
     * ]
     * </pre></code>
     * </p>
     *
     * <p>
     * For builder-style construction, see {@link #table(Closure)}.
     * </p>
     *
     * @param   tableMatrix
     *
     * A nested <code>List</code> structure, where the outer list is
     * the <code>[table][/table]</code> element, the inner
     * <code>List</code>s are the <code>[td][/td]</code> elements,
     * and the items in the inner <code>List</code> are the the
     * <code>[td][/td]</code> elements.
     *
     * @return
     *
     * A <code>String</code>, where
     * <code>[["a", "b", "c"], [1, 2, 3]] =</code>
     * <code>[table][tr][td]a[/td][td]b[/td][td]c[/td][/tr]
     * &#08;[tr][td]1[/td][td]2[/td][td]3[/td][/tr][/table]</code>.
     */
    public final String table(List tableMatrix) {
        new BBCodeTableBuilder().render(tableMatrix)
    }

    public final String table(Closure closure) {
        new BBCodeTableBuilder().render(closure)
    }
}
