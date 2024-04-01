package com.betrybe.currencyview

import android.content.Context
import android.text.InputType
import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withInputType
import androidx.test.espresso.matcher.ViewMatchers.withParent
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.betrybe.currencyview.ui.views.activities.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

private fun getId(id: String): Int {
    val targetContext: Context = InstrumentationRegistry.getInstrumentation().targetContext
    val packageName: String = targetContext.packageName
    return targetContext.resources.getIdentifier(id, "id", packageName)
}

@RunWith(AndroidJUnit4::class)
class MainActivityAndroidTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun test_req_1_crie_a_estrutura_inicial_da_tela_com_o_nome_da_aplicacao() {
        onView(withId(getId("main")))
            // O elemento ConstraintLayout está visível?
            .check(matches(isDisplayed()))
            // O elemento ConstraintLayout possui a mesma largura do pai?
            .check(matches(withLayoutWidthEqualsToMatchParent()))
            // O elemento ConstraintLayout possui a mesma altura do pai?
            .check(matches(withLayoutHeightEqualsToMatchParent()))

        onView(withId(getId("linear_container")))
            // O elemento LinearLayout está visível?
            .check(matches(isDisplayed()))
            // O elemento LinearLayout é filho do elemento ConstraintLayout?
            .check(matches(withParent(withId(getId("main")))))
            // O elemento LinearLayout possui a mesma largura do elemento pai?
            .check(matches(withLayoutWidthEqualsToMatchParent()))
            // O elemento LinearLayout possui a mesma altura do elemento pai?
            .check(matches(withLayoutHeightEqualsToMatchParent()))
            // O elemento LinearLayout possui orientação vertical?
            .check(matches(withLinearLayoutVerticalOrientation()))
            // O elemento LinearLayout possui margens iguais a 16dp?
            .check(matches(withLayoutMarginsEqualsTo(16)))
            // O elemento LinearLayout possui as restrições limitadas ao elemento pai?
            .check(matches(withLayoutConstraintsOfParent()))

        onView(withId(getId("application_name")))
            // O elemento MaterialTextView está visível?
            .check(matches(isDisplayed()))
            // O elemento MaterialTextView é filho do elemento LinearLayout?
            .check(matches(withParent(withId(getId("linear_container")))))
            // O elemento MaterialTextView possui o texto Conversor de Moedas?
            .check(matches(withText("Conversor de Moedas")))
            // O elemento MaterialTextView possui a mesma largura do elemento pai?
            .check(matches(withLayoutWidthEqualsToMatchParent()))
            // O elemento MaterialTextView possui a largura necessária para envolver seu conteúdo?
            .check(matches(withLayoutHeightEqualsToWrapContent()))
            // O elemento MaterialTextView possui o texto centralizado?
            .check(matches(withMaterialTextViewGravityEqualsToCenter()))
            // O elemento MaterialTextView possui margem inferior igual a 16dp?
            .check(matches(withLayoutMarginBottomEqualsTo(16)))
    }

    @Test
    fun test_req_2_crie_o_componente_de_selecao_de_moedas() {
        onView(withId(getId("currency_selection_input_container")))
            // O elemento TextInputLayout está visível?
            .check(matches(isDisplayed()))
            // O elemento TextInputLayout é filho do elemento LinearLayout?
            .check(matches(withParent(withId(getId("linear_container")))))
            // O elemento TextInputLayout possui a mesma largura do elemento pai?
            .check(matches(withLayoutWidthEqualsToMatchParent()))
            // O elemento TextInputLayout possui a largura necessária para envolver seu conteúdo?
            .check(matches(withLayoutHeightEqualsToWrapContent()))
            // O elemento TextInputLayout possui margem inferior igual a 16dp?
            .check(matches(withLayoutMarginBottomEqualsTo(16)))
            // O elemento TextInputLayout possui a label (hint) igual a Moeda?
            .check(matches(withTextInputLayoutHintEqualsTo("Moeda")))

        onView(withId(getId("currency_selection_input_layout")))
            // O elemento AutoCompleteTextView está visível?
            .check(matches(isDisplayed()))
            // O elemento AutoCompleteTextView é filho do elemento TextInputLayout?
            .check(matches(withParentEqualsTo(getId("currency_selection_input_container"))))
            // O elemento AutoCompleteTextView possui a mesma largura do elemento pai?
            .check(matches(withLayoutWidthEqualsToMatchParent()))
            // O elemento AutoCompleteTextView possui a largura necessária para envolver seu conteúdo?
            .check(matches(withLayoutHeightEqualsToWrapContent()))
            // O elemento AutoCompleteTextView possui inputType igual a none?
            .check(matches(withInputType(InputType.TYPE_NULL)))
    }

    @Test
    fun test_req_3_crie_o_componente_de_estado_carregando_moedas() {
        onView(withId(getId("load_currency_state")))
            // O elemento MaterialTextView é filho do elemento LinearLayout?
            .check(matches(withParent(withId(getId("linear_container")))))
            // O elemento MaterialTextView possui a mesma largura do elemento pai?
            .check(matches(withLayoutWidthEqualsToMatchParent()))
            // O elemento MaterialTextView possui a mesma altura do elemento pai?
            .check(matches(withLayoutHeightEqualsToMatchParent()))
            // O elemento MaterialTextView possui o texto centralizado?
            .check(matches(withMaterialTextViewGravityEqualsToCenter()))
            // O elemento MaterialTextView possui o texto igual a Carregando Moedas...?
            .check(matches(withText("Carregando moedas...")))
            // O elemento MaterialTextView possui margem inferior igual a 16dp?
            .check(matches(withMaterialTextViewTextSizeEqualsTo(16)))
    }

    @Test
    fun test_req_4_crie_o_componente_de_estado_selecione_uma_moeda() {
        onView(withId(getId("select_currency_state")))
            // O elemento MaterialTextView é filho do elemento LinearLayout?
            .check(matches(withParent(withId(getId("linear_container")))))
            // O elemento MaterialTextView possui a mesma largura do elemento pai?
            .check(matches(withLayoutWidthEqualsToMatchParent()))
            // O elemento MaterialTextView possui a mesma altura do elemento pai?
            .check(matches(withLayoutHeightEqualsToMatchParent()))
            // O elemento MaterialTextView possui a margem superior a 24dp?
            .check(matches(withLayoutMarginTopEqualsTo(24)))
            // O elemento MaterialTextView possui texto igual a Selecione uma moeda.?
            .check(matches(withText("Selecione uma moeda.")))
            // O elemento MaterialTextView possui o texto centralizado?
            .check(matches(withMaterialTextViewGravityEqualsToCenter()))
            // O elemento MaterialTextView possui o tamanho do texto igual a 16dp?
            .check(matches(withMaterialTextViewTextSizeEqualsTo(16)))
    }

    @Test
    fun test_req_5_crie_o_componente_de_estado_aguardando_resposta() {
        onView(withId(getId("waiting_response_state")))
            // O elemento FrameLayout é filho do elemento LinearLayout?
            .check(matches(withParent(withId(getId("linear_container")))))
            // O elemento FrameLayout possui a mesma largura do elemento pai?
            .check(matches(withLayoutWidthEqualsToMatchParent()))
            // O elemento FrameLayout possui a mesma altura do elemento pai?
            .check(matches(withLayoutHeightEqualsToMatchParent()))
            // O elemento FrameLayout possui visibilidade igual a GONE?
            .check(matches(withVisibility(View.GONE)))

        onView(withId(getId("waiting_response_progress")))
            // O elemento CircularProgressIndicator é filho do elemento LinearLayout?
            .check(matches(withParent(withId(getId("waiting_response_state")))))
            // O elemento CircularProgressIndicator possui a mesma largura do elemento pai?
            .check(matches(withLayoutWidthEqualsToMatchParent()))
            // O elemento CircularProgressIndicator possui a mesma altura do elemento pai?
            .check(matches(withLayoutHeightEqualsToMatchParent()))
            // O elemento CircularProgressIndicator está centralizado em relação ao componente pai?
            .check(matches(withLayoutGravityEqualsToCenter()))
            // O elemento CircularProgressIndicator está com a propriedade indeterminate com valor igual a true?
            .check(matches(withCircularProgressIndicatorIndeterminate()))
    }

    @Test
    fun test_req_6_crie_o_componente_de_exibicao_das_taxas_de_conversao_das_moedas() {
        onView(withId(getId("currency_rates_state")))
            // O elemento RecyclerView é filho do elemento LinearLayout?
            .check(matches(withParent(withId(getId("linear_container")))))
            // O elemento RecyclerView possui a mesma largura do elemento pai?
            .check(matches(withLayoutWidthEqualsToMatchParent()))
            // O elemento RecyclerView possui a mesma altura do elemento pai?
            .check(matches(withLayoutHeightEqualsToMatchParent()))
            // O elemento FrameLayout possui visibilidade igual a GONE?
            .check(matches(withVisibility(View.GONE)))
    }

//    @Test
//    fun test_req7() {
//        onView(withId(getId("connection_error_state")))
//            // O elemento ImageView é filho do elemento LinearLayout?
//            .check(matches(withParent(withId(getId("linear_container")))))
//            // O elemento ImageView possui largura igual a 250dp?
//            .check(matches(withLayoutWidthEqualsTo(200)))
//            // O elemento ImageView possui a mesma altura do elemento pai?
//            .check(matches(withLayoutHeightEqualsToMatchParent()))
//            // O elemento ImageView está centralizado em relação ao componente pai?
//            .check(matches(withLayoutGravityEqualsToCenter()))
//            // O elemento ImageView possui margem superior igual a 24dp?
//            .check(matches(withLayoutMarginTopEqualsTo(24)))
//            // O elemento ImageView possui margem inferior igual a 24dp?
//            .check(matches(withLayoutMarginBottomEqualsTo(24)))
//            // O elemento ImageView possui um drawable definido?
//            .check(matches(hasDrawable()))
//            // O elemento ImageView possui visibilidade igual a GONE?
//            .check(matches(withVisibility(View.GONE)))
//    }
}
