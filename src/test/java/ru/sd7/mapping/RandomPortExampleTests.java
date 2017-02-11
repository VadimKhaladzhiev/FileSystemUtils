package ru.sd7.mapping;

import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.sd7.model.SearchResult;
import ru.sd7.rest.SearchResultRestService;
import ru.sd7.services.spring.api.SearchResultService;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ContextConfiguration(classes = ru.sd7.rest.RestService.class)
@RunWith(SpringRunner.class)
@WebMvcTest(SearchResultRestService.class)
public class RandomPortExampleTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private SearchResultService searchResultService;

    @Test
    public void testExample() throws Exception {
        given(this.searchResultService.get(643L))
                .willReturn(new SearchResult());
        this.mvc.perform(get("/search_results/by_id/643").accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk()).andExpect(content().string("{\"id\":null,\"threadName\":null,\"filePath\":null,\"lineNumber\":null,\"line\":null}"));
    }

}