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

    public final String render(Closure closure) {
        run closure

        render()
    }

    public final String render(List tableMatrix) {
        this.tableMatrix = tableMatrix

        render()
    }

    public final String render() {
        StringBuffer table = new StringBuffer("[table][/table]")

        tableMatrix.each { tr ->
            table.insert((table.size() - 8), "[tr][/tr]")
            tr.each { td ->
                table.insert((table.size() - 13), "[td]${td}[/td]")
            }
        }

        return (table as String)
    }

    public String toString() {
        render()
    }

    private final void run(Closure closure) {
        closure.delegate = this
        closure.resolveStrategy = Closure.DELEGATE_FIRST
        closure.call()
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

    public final String table(List tableMatrix) {
        bbCodeBuilder.table(tableMatrix)
    }

    public final String table(Closure closure) {
        bbCodeBuilder.table(closure)
    }
}
