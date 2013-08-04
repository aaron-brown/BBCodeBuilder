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
 * Building Tables is done through either the {@link #table(List)} or
 * {@link #table(Closure)} functions, which are implemented by
 * <b><code>BBCodeTableBuilder</code></b>.
 *
 * @see groovy.bbcode.BBCodeTableBuilder
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
     * Construct a BBCode Table out of a pre-established
     * <code>tableMatrix</code>-like structure.
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
        new BBCodeTableBuilder().table(tableMatrix)
    }

    /**
     * Build a table.
     *
     * <p>
     * Sample Usage:
     *
     * <code><pre>
     * String table = new BBCodeBuilder().table {
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
     * <code>tr(Object...)</code> and possibly
     * <code>subTable(Closure)</code> calls.
     *
     * @return
     *
     * A <code>[table][tr][td][/td][/tr][/table]</code> structure, as
     * derived by order and nature of the <code>tr(Object...)</code>
     * and possibly <code>subTable(Closure)</code> calls.
     */
    public final String table(Closure closure) {
        new BBCodeTableBuilder().table(closure)
    }
}
