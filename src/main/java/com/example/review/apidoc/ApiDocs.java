package com.example.review.apidoc;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * REST API Docs ìëí ëì ë©ìë íì.
 *
 * ì´ ì´ë¸íì´ìì´ ë¶ì @*Mapping ë©ìëë ë¡ì»¬ /api-docs ëªë ¹ì¼ë¡ docs/ ëë í ë¦¬ì
 * md íì¼ê³¼ ë§¤íëì´ ìë ë¬¸ìíÂ·ì½ë ë¦¬ë·° íë¦ì í¬í¨ë©ëë¤.
 *
 * <pre>
 *   {@literal @}ApiDocs(title = "Todo ë¨ê±´ ì¡°í")
 *   {@literal @}GetMapping("/{id}")
 *   public TodoResponse getById(...) { ... }
 * </pre>
 *
 * <ul>
 *   <li>title ìì â docs/{slugify(title)}.md</li>
 *   <li>title ìì â docs/{method}-{slugify(url)}.md</li>
 * </ul>
 *
 * SOURCE retention ì´ë¼ ì»´íì¼ ê²°ê³¼ë¬¼ì ì ë¨ê³  ë°íì ìí¥ 0.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.SOURCE)
public @interface ApiDocs {
    String title() default "";
}
