package net.jagunma.backbone.auth.authmanager.infra.web.oa12010;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import net.jagunma.backbone.auth.authmanager.application.commandService.CheckBizTranRoleComposition;
import net.jagunma.backbone.auth.authmanager.application.commandService.StoreBizTranRoleComposition;
import net.jagunma.backbone.auth.authmanager.application.commandService.WriteBizTranRoleComposition;
import net.jagunma.backbone.auth.authmanager.application.queryService.RaedBizTranRoleComposition;
import net.jagunma.backbone.auth.authmanager.application.queryService.SearchBizTranRoleComposition;
import net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleCompositionCommand.BizTranRoleCompositionImportCheckRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleCompositionCommand.BizTranRoleCompositionImportCheckResponse;
import net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleCompositionCommand.BizTranRoleCompositionImportRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleCompositionCommand.BizTranRoleCompositionImportResponse;
import net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleCompositionExcelCommand.BizTranRoleCompositionExcelWriteRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleCompositionExcelCommand.BizTranRoleCompositionExcelWriteResponse;
import net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleCompositionExcelReference.BizTranRoleCompositionExcelReadRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleCompositionExcelReference.BizTranRoleCompositionExcelReadResponse;
import net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleCompositionReference.BizTranRoleCompositionExportRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleCompositionReference.BizTranRoleCompositionExportResponse;
import net.jagunma.backbone.auth.authmanager.infra.web.common.SelectOptionItemSource;
import net.jagunma.backbone.auth.authmanager.infra.web.common.SelectOptionItemsSource;
import net.jagunma.backbone.auth.authmanager.infra.web.oa12010.dto.Oa12010Dto;
import net.jagunma.backbone.auth.authmanager.infra.web.oa12010.vo.Oa12010MessageVo;
import net.jagunma.backbone.auth.authmanager.infra.web.oa12010.vo.Oa12010Vo;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.BizTranRoleComposition;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.BizTranRoleCompositionRepositoryForStore;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp_BizTran.BizTranGrp_BizTranCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp_BizTran.BizTranGrp_BizTranRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp_BizTran.BizTranGrp_BizTrans;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole.BizTranRoleCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole.BizTranRoleRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole.BizTranRoles;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole_BizTranGrp.BizTranRole_BizTranGrpCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole_BizTranGrp.BizTranRole_BizTranGrpRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole_BizTranGrp.BizTranRole_BizTranGrps;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRoleCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRoleRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRoles;
import net.jagunma.backbone.auth.authmanager.model.excel.ExcelContainer;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranGrp_BizTranSheet;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranGrp_BizTransSheet;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranRoleCompositionBookRepositoryForRead;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranRoleCompositionBookRepositoryForWrite;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranRole_BizTranGrpSheet;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranRole_BizTranGrpsSheet;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
import net.jagunma.common.ddd.model.orders.Orders;
import net.jagunma.common.tests.constants.TestSize;
import net.jagunma.common.util.base.Preconditions;
import net.jagunma.common.util.exception.GunmaRuntimeException;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

class Oa12010ControllerTest {

    // ?????? ??? ?????? ?????????
    private String mode = "import";
    private String subSystemCode = "";
    private List<SelectOptionItemSource> subSystemList = SelectOptionItemsSource.createFrom(SubSystem.getValidValues()).getValue();
    private byte[] exportExcelBook = null;
    private BizTranRole_BizTranGrpsSheet bizTranRole_BizTranGrpsSheet = null;
    private BizTranGrp_BizTransSheet bizTranGrp_BizTransSheet = null;
    private List<Oa12010MessageVo> messageVoList = null;

    private byte[] actualHttpServletResponseWrite = null;

    private final String GunmaRuntimeExceptionMessageCode = "EOA14002";
    private final String GunmaRuntimeExceptionMessageArg1 = "??????????????????";
    private final String GunmaRuntimeExceptionMessageArg2 = "???????????????";

    // ByteArrayOutputStream???????????????
    private ByteArrayOutputStream createByteArrayOutputStream() {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        os.write( 0x00 );
        os.write( 0x01 );
        os.write( 0x02 );
        os.write( 0x03 );
        return os;
    }

    // OA12010 View Object??????
    private Oa12010Vo createOa12010Vo() {
        Oa12010Vo vo = new Oa12010Vo();
        vo.setMode(mode);
        vo.setSubSystemCode(subSystemCode);
        vo.setSubSystemList(subSystemList);
        vo.setExportExcelBook(exportExcelBook);
        vo.setMessageVoList(messageVoList);
        return vo;
    }
    // OA12010 Import????????? Dto??????
    private Oa12010Dto createOa12010Dto() {
        Oa12010Dto dto = new Oa12010Dto();
        dto.setSubSystemCode(subSystemCode);
        dto.setBizTranRole_BizTranGrpsSheet(bizTranRole_BizTranGrpsSheet);
        dto.setBizTranGrp_BizTransSheet(bizTranGrp_BizTransSheet);
        return dto;
    }

    // ??????????????????????????????
    private Oa12010Controller createOa12010Controller(Integer throwExceptio) {

        // ?????????????????????????????????????????????????????????
        SearchBizTranRoleComposition searchBizTranRoleComposition = new SearchBizTranRoleComposition(
            new BizTranRole_BizTranGrpRepository() {
                @Override
                public BizTranRole_BizTranGrps selectBy(BizTranRole_BizTranGrpCriteria bizTranRole_BizTranGrpCriteria, Orders orders) {
                    return null;
                }
                @Override
                public BizTranRole_BizTranGrps selectAll(Orders orders) {
                    return null;
                }
            },
            new BizTranGrp_BizTranRepository() {
                @Override
                public BizTranGrp_BizTrans selectBy(BizTranGrp_BizTranCriteria bizTranGrp_BizTranCriteria, Orders orders) {
                    return null;
                }
                @Override
                public BizTranGrp_BizTrans selectAll(Orders orders) {
                    return null;
                }
            }) {

            public void execute(BizTranRoleCompositionExportRequest request,BizTranRoleCompositionExportResponse response) {
                if (throwExceptio == null) { return;}
                // createOa12010Controller????????? throwExceptio == -1 ????????????RuntimeException ??????????????????
                if (throwExceptio == -1) {
                    throw new RuntimeException();
                }
                // createOa12010Controller????????? throwExceptio == -2 ????????????GunmaRuntimeException ??????????????????
                if (throwExceptio == -2) {
                    Preconditions.checkNotEmpty("", () -> new GunmaRuntimeException(GunmaRuntimeExceptionMessageCode, GunmaRuntimeExceptionMessageArg1, GunmaRuntimeExceptionMessageArg2));
                }
            }
        };
        // ???????????????????????????????????????Excel Write????????????
        WriteBizTranRoleComposition writeBizTranRoleComposition = new WriteBizTranRoleComposition(
            new BizTranRoleCompositionBookRepositoryForWrite() {
                @Override
                public ExcelContainer create(
                    BizTranRole_BizTranGrpsSheet bizTranRole_BizTranGrpsSheet,
                    BizTranGrp_BizTransSheet bizTranGrp_BizTransSheet) {
                    return null;
                }
            }) {

            public void execute(BizTranRoleCompositionExcelWriteRequest request, BizTranRoleCompositionExcelWriteResponse response) {
                response.setExcelContainer(ExcelContainer.createFrom(createByteArrayOutputStream()));
            }
        };
        // ????????????????????????????????????Excel Raed????????????
        RaedBizTranRoleComposition raedBizTranRoleComposition = new RaedBizTranRoleComposition(
            new BizTranRoleCompositionBookRepositoryForRead() {
                @Override
                public void read(ExcelContainer excelContainer,
                    List<BizTranRole_BizTranGrpSheet> bizTranRole_BizTranGrpSheetList,
                    List<BizTranGrp_BizTranSheet> bizTranGrp_BizTranSheetList) {

                }
            }) {
            public void execute(BizTranRoleCompositionExcelReadRequest request, BizTranRoleCompositionExcelReadResponse response) {
                if (throwExceptio == null) { return;}
                // createOa12010Controller????????? throwExceptio == -1 ????????????RuntimeException ??????????????????
                if (throwExceptio == -1) {
                    throw new RuntimeException();
                }
                // createOa12010Controller????????? throwExceptio == -2 ????????????GunmaRuntimeException ??????????????????
                if (throwExceptio == -2) {
                    Preconditions.checkNotEmpty("", () -> new GunmaRuntimeException(GunmaRuntimeExceptionMessageCode, GunmaRuntimeExceptionMessageArg1, GunmaRuntimeExceptionMessageArg2));
                }
            }
        };
        // ???????????????????????????????????????Excel ??????????????????
        StoreBizTranRoleComposition storeBizTranRoleComposition = new StoreBizTranRoleComposition(
            new BizTranRoleCompositionRepositoryForStore() {
                @Override
                public BizTranRoleComposition store(BizTranRoleComposition bizTranRoleComposition) {
                    return null;
                }
            }
        ) {
            public void execute(BizTranRoleCompositionImportRequest request,BizTranRoleCompositionImportResponse response) {
                if (throwExceptio == null) { return;}
                // createOa12010Controller????????? throwExceptio == -1 ????????????RuntimeException ??????????????????
                if (throwExceptio == -1) {
                    throw new RuntimeException();
                }
                // createOa12010Controller????????? throwExceptio == -2 ????????????GunmaRuntimeException ??????????????????
                if (throwExceptio == -2) {
                    Preconditions.checkNotEmpty("", () -> new GunmaRuntimeException(GunmaRuntimeExceptionMessageCode, GunmaRuntimeExceptionMessageArg1, GunmaRuntimeExceptionMessageArg2));
                }
            }
        };

        // ???????????????????????????????????????Excel ????????????????????????
        CheckBizTranRoleComposition checkBizTranRoleComposition = new CheckBizTranRoleComposition(
            new BizTranRoleRepository() {
                @Override
                public BizTranRoles selectBy(BizTranRoleCriteria bizTranRoleCriteria, Orders orders) {
                    return null;
                }
                @Override
                public BizTranRoles selectAll(Orders orders) {
                    return null;
                }
            },
            new Operator_BizTranRoleRepository() {
                @Override
                public Operator_BizTranRoles selectBy(Operator_BizTranRoleCriteria operator_BizTranRoleCriteria, Orders orders) {
                    return null;
                }
            }
        ) {
            public void execute(BizTranRoleCompositionImportCheckRequest request, BizTranRoleCompositionImportCheckResponse response) {
            }
        };

        return new Oa12010Controller(
            searchBizTranRoleComposition,
            writeBizTranRoleComposition,
            raedBizTranRoleComposition,
            storeBizTranRoleComposition,
            checkBizTranRoleComposition);
    }
    // HttpServletResponse??????
    private HttpServletResponse createHttpServletResponse() {
        return new HttpServletResponse() {
            @Override
            public ServletOutputStream getOutputStream() throws IOException {
                return new ServletOutputStream() {
                    @Override
                    public void write(byte b[]) {
                        actualHttpServletResponseWrite = b;
                    }
                    @Override
                    public void write(int b) throws IOException {
                    }
                    @Override
                    public boolean isReady() {
                        return false;
                    }
                    @Override
                    public void setWriteListener(WriteListener listener) {
                    }
                };
            }
            @Override
            public String getCharacterEncoding() {
                return null;
            }
            @Override
            public String getContentType() {
                return null;
            }
            @Override
            public PrintWriter getWriter() throws IOException {
                return null;
            }
            @Override
            public void setCharacterEncoding(String charset) {
            }
            @Override
            public void setContentLength(int len) {
            }
            @Override
            public void setContentLengthLong(long length) {
            }
            @Override
            public void setContentType(String type) {
            }
            @Override
            public void setBufferSize(int size) {
            }
            @Override
            public int getBufferSize() {
                return 0;
            }
            @Override
            public void flushBuffer() throws IOException {
            }
            @Override
            public void resetBuffer() {
            }
            @Override
            public boolean isCommitted() {
                return false;
            }
            @Override
            public void reset() {
            }
            @Override
            public void setLocale(Locale loc) {
            }
            @Override
            public Locale getLocale() {
                return null;
            }
            @Override
            public void addCookie(Cookie cookie) {
            }
            @Override
            public boolean containsHeader(String name) {
                return false;
            }
            @Override
            public String encodeURL(String url) {
                return null;
            }
            @Override
            public String encodeRedirectURL(String url) {
                return null;
            }
            @Override
            @SuppressWarnings("deprecation")
            public String encodeUrl(String url) {
                return null;
            }
            @Override
            @SuppressWarnings("deprecation")
            public String encodeRedirectUrl(String url) {
                return null;
            }
            @Override
            public void sendError(int sc, String msg) throws IOException {
            }
            @Override
            public void sendError(int sc) throws IOException {
            }
            @Override
            public void sendRedirect(String location) throws IOException {
            }
            @Override
            public void setDateHeader(String name, long date) {
            }
            @Override
            public void addDateHeader(String name, long date) {
            }
            @Override
            public void setHeader(String name, String value) {
            }
            @Override
            public void addHeader(String name, String value) {
            }
            @Override
            public void setIntHeader(String name, int value) {
            }
            @Override
            public void addIntHeader(String name, int value) {
            }
            @Override
            public void setStatus(int sc) {
            }
            @Override
            @SuppressWarnings("deprecation")
            public void setStatus(int sc, String sm) {
            }
            @Override
            public int getStatus() {
                return 0;
            }
            @Override
            public String getHeader(String name) {
                return null;
            }
            @Override
            public Collection<String> getHeaders(String name) {
                return null;
            }
            @Override
            public Collection<String> getHeaderNames() {
                return null;
            }
        };
    }
    // importfile??????
    private MultipartFile createMultipartFile() {
        return new MultipartFile() {
            @Override
            public byte[] getBytes() throws IOException {
                return createByteArrayOutputStream().toByteArray();
            }
            @Override
            public String getName() {
                return null;
            }
            @Override
            public String getOriginalFilename() {
                return null;
            }
            @Override
            public String getContentType() {
                return null;
            }
            @Override
            public boolean isEmpty() {
                return false;
            }
            @Override
            public long getSize() {
                return 0;
            }
            @Override
            public InputStream getInputStream() throws IOException {
                return null;
            }
            @Override
            public void transferTo(File dest) throws IOException, IllegalStateException {
            }
        };
    }

    /**
     * {@link Oa12010Controller#get(Model)}?????????
     *  ???????????????
     *    ??????
     *
     *  ???????????????
     *  ???Vo???????????????
     */
    @Test
    @Tag(TestSize.SMALL)
    void get_test0() {

        // ??????????????????????????????
        Oa12010Controller oa12010Controller = createOa12010Controller(null);

        // ?????????
        ConcurrentModel model = new ConcurrentModel();

        // ?????????
        String expectedViewName = "oa12010";
        Oa12010Vo expectedVo = createOa12010Vo();

        // ??????
        String actualViewName = oa12010Controller.get(model);
        Oa12010Vo actualVo = (Oa12010Vo) model.getAttribute("form");

        // ????????????
        assertThat(actualViewName).isEqualTo(expectedViewName);
        assertThat(actualVo).usingRecursiveComparison().isEqualTo(expectedVo);
    }

    /**
     * {@link Oa12010Controller#get(Model)}?????????
     *  ???????????????
     *    ?????????GunmaRuntimeException?????????
     *
     *  ???????????????
     *  ?????????
     */
    @Test
    @Tag(TestSize.SMALL)
    void get_test1() {
        // get???????????????GunmaRuntimeException????????????????????????????????????
        assertThat(true);
    }

    /**
     * {@link Oa12010Controller#get(Model)}?????????
     *  ???????????????
     *    ?????????RuntimeException?????????
     *
     *  ???????????????
     *  ?????????
     */
    @Test
    @Tag(TestSize.SMALL)
    void get_test2() {
        // get???????????????RuntimeException??????????????????????????????????????????
        assertThat(true);
    }

    /**
     * {@link Oa12010Controller#exportExcel(Model,Oa12010Vo,HttpServletResponse)}?????????
     *  ???????????????
     *    ??????
     *
     *  ???????????????
     *  ???HttpServletResponse Write
     */
    @Test
    @Tag(TestSize.SMALL)
    void exportExcel_test0() {

        // ??????????????????????????????
        Oa12010Controller oa12010Controller = createOa12010Controller(null);

        // ?????????
        ConcurrentModel model = new ConcurrentModel();
        subSystemCode = SubSystem.??????_??????.getCode();
        Oa12010Vo Oa12010Vo = createOa12010Vo();
        HttpServletResponse response = createHttpServletResponse();

        // ?????????
        String expectedViewName = null;
        Oa12010Vo expectedVo = createOa12010Vo();
        byte[] expectedExportExcelBook = createByteArrayOutputStream().toByteArray();

        // ??????
        String actualViewName = oa12010Controller.exportExcel(model, Oa12010Vo, response);

        // ????????????
        assertThat(actualViewName).isEqualTo(expectedViewName);
        assertThat(actualHttpServletResponseWrite).isEqualTo(expectedExportExcelBook);
    }

    /**
     * {@link Oa12010Controller#exportExcel(Model,Oa12010Vo,HttpServletResponse)}?????????
     *  ???????????????
     *    ??????
     *    ???ViewModel??????????????????????????????
     *
     *  ???????????????
     *  ???HttpServletResponse Write
     */
    @Test
    @Tag(TestSize.SMALL)
    void exportExcel_test1() {

        // ??????????????????????????????
        Oa12010Controller oa12010Controller = createOa12010Controller(null);

        // ?????????
        ConcurrentModel model = new ConcurrentModel();
        Oa12010Vo Oa12010Vo = createOa12010Vo();
        HttpServletResponse response = createHttpServletResponse();

        // ?????????
        String expectedViewName = null;
        Oa12010Vo expectedVo = createOa12010Vo();
        byte[] expectedExportExcelBook = createByteArrayOutputStream().toByteArray();

        // ??????
        String actualViewName = oa12010Controller.exportExcel(model, Oa12010Vo, response);

        // ????????????
        assertThat(actualViewName).isEqualTo(expectedViewName);
        assertThat(actualHttpServletResponseWrite).isEqualTo(expectedExportExcelBook);
    }


    /**
     * {@link Oa12010Controller#exportExcel(Model,Oa12010Vo,HttpServletResponse)}?????????
     *  ???????????????
     *    ?????????RuntimeException?????????
     *
     *  ???????????????
     *  ????????????
     *  ???????????????????????????????????????
     */
    @Test
    @Tag(TestSize.SMALL)
    void exportExcel_test2() {

        // ??????????????????????????????
        Oa12010Controller oa12010Controller = createOa12010Controller(-1);

        // ?????????
        ConcurrentModel model = new ConcurrentModel();
        Oa12010Vo Oa12010Vo = createOa12010Vo();
        HttpServletResponse response = createHttpServletResponse();

        // ?????????
        String expectedViewName = "oa19999";
        String expectedMessageCode = "EOA10001";

        // ??????
        String actualViewName = oa12010Controller.exportExcel(model, Oa12010Vo, response);
        Oa12010Vo actualVo = (Oa12010Vo) model.getAttribute("form");

        // ????????????
        assertThat(actualViewName).isEqualTo(expectedViewName);
        assertThat(actualVo.getMessageCode()).isEqualTo(expectedMessageCode);
    }

    /**
     * {@link Oa12010Controller#exportExcel(Model,Oa12010Vo,HttpServletResponse)}?????????
     *  ???????????????
     *    ?????????GunmaRuntimeException ?????????
     *
     *  ???????????????
     *  ????????????
     *  ???????????????????????????????????????
     */
    @Test
    @Tag(TestSize.SMALL)
    void exportExcel_test3() {

        // ??????????????????????????????
        Oa12010Controller oa12010Controller = createOa12010Controller(-2);

        // ?????????
        ConcurrentModel model = new ConcurrentModel();
        Oa12010Vo Oa12010Vo = createOa12010Vo();
        HttpServletResponse response = createHttpServletResponse();

        // ?????????
        String expectedViewName = "oa12010";

        // ??????
        String actualViewName = oa12010Controller.exportExcel(model, Oa12010Vo, response);
        Oa12010Vo actualVo = (Oa12010Vo) model.getAttribute("form");

        // ????????????
        assertThat(actualViewName).isEqualTo(expectedViewName);
        assertThat(actualVo.getMessageCode()).isEqualTo(GunmaRuntimeExceptionMessageCode);
        assertThat(actualVo.getMessageArgs().get(0)).isEqualTo(GunmaRuntimeExceptionMessageArg1);
        assertThat(actualVo.getMessageArgs().get(1)).isEqualTo(GunmaRuntimeExceptionMessageArg2);
    }

    /**
     * {@link Oa12010Controller#exportExcel(Model,Oa12010Vo,HttpServletResponse)}?????????
     *  ???????????????
     *    ??????????????????response????????????IOException?????????
     *
     *  ???????????????
     */
    @Test
    @Tag(TestSize.SMALL)
    void exportExcel_test4() {
        // TODO: exportExcel???????????????IOException??????????????????????????????????????????
        assertThat(true);
    }

    /**
     * {@link Oa12010Controller#checkExcel(MultipartFile,Model,Oa12010Vo)}?????????
     *  ???????????????
     *    ??????
     *
     *  ???????????????
     *  ???Vo???????????????
     */
    @Test
    @Tag(TestSize.SMALL)
    void checkExcel_test0() {

        // ??????????????????????????????
        Oa12010Controller oa12010Controller = createOa12010Controller(null);

        // ?????????
        ConcurrentModel model = new ConcurrentModel();
        subSystemCode = SubSystem.??????_??????.getCode();
        Oa12010Vo Oa12010Vo = createOa12010Vo();
        MultipartFile multipartFile = createMultipartFile();
        MockHttpServletRequest mockHttpServletReques = new MockHttpServletRequest();
        oa12010Controller.session = mockHttpServletReques.getSession();

        // ?????????
        String expectedViewName = "oa12010";
//        subSystemCode = SubSystem.??????_??????.getCode();
        messageVoList = newArrayList();
        Oa12010Vo expectedVo = createOa12010Vo();

        // ??????
        String actualViewName = oa12010Controller.checkExcel(multipartFile, model, Oa12010Vo);
        Oa12010Vo actualVo = (Oa12010Vo) model.getAttribute("form");

        // ????????????
        assertThat(actualViewName).isEqualTo(expectedViewName);
        assertThat(actualVo).usingRecursiveComparison().isEqualTo(expectedVo);
    }

    /**
     * {@link Oa12010Controller#checkExcel(MultipartFile,Model,Oa12010Vo)}?????????
     *  ???????????????
     *    ?????????RuntimeException?????????
     *
     *  ???????????????
     *  ????????????
     *  ???????????????????????????????????????
     */
    @Test
    @Tag(TestSize.SMALL)
    void checkExcel_test1() {

        // ??????????????????????????????
        Oa12010Controller oa12010Controller = createOa12010Controller(-1);

        // ?????????
        ConcurrentModel model = new ConcurrentModel();
        Oa12010Vo Oa12010Vo = createOa12010Vo();
        MultipartFile multipartFile = createMultipartFile();

        // ?????????
        String expectedViewName = "oa19999";
        String expectedMessageCode = "EOA10001";

        // ??????
        String actualViewName = oa12010Controller.checkExcel(multipartFile, model, Oa12010Vo);
        Oa12010Vo actualVo = (Oa12010Vo) model.getAttribute("form");

        // ????????????
        assertThat(actualViewName).isEqualTo(expectedViewName);
        assertThat(actualVo.getMessageCode()).isEqualTo(expectedMessageCode);
    }

    /**
     * {@link Oa12010Controller#checkExcel(MultipartFile,Model,Oa12010Vo)}?????????
     *  ???????????????
     *    ?????????GunmaRuntimeException ?????????
     *
     *  ???????????????
     *  ????????????
     *  ???????????????????????????????????????
     */
    @Test
    @Tag(TestSize.SMALL)
    void checkExcel_test2() {

        // ??????????????????????????????
        Oa12010Controller oa12010Controller = createOa12010Controller(-2);

        // ?????????
        ConcurrentModel model = new ConcurrentModel();
        Oa12010Vo Oa12010Vo = createOa12010Vo();
        MultipartFile multipartFile = createMultipartFile();

        // ?????????
        String expectedViewName = "oa12010";

        // ??????
        String actualViewName = oa12010Controller.checkExcel(multipartFile, model, Oa12010Vo);
        Oa12010Vo actualVo = (Oa12010Vo) model.getAttribute("form");

        // ????????????
        assertThat(actualViewName).isEqualTo(expectedViewName);
        assertThat(actualVo.getMessageCode()).isEqualTo(GunmaRuntimeExceptionMessageCode);
        assertThat(actualVo.getMessageArgs().get(0)).isEqualTo(GunmaRuntimeExceptionMessageArg1);
        assertThat(actualVo.getMessageArgs().get(1)).isEqualTo(GunmaRuntimeExceptionMessageArg2);
    }

    /**
     * {@link Oa12010Controller#checkExcel(MultipartFile,Model,Oa12010Vo)}?????????
     *  ???????????????
     *    ?????????IOException  ?????????
     *
     *  ???????????????
     */
    @Test
    @Tag(TestSize.SMALL)
    void checkExcel_test3() {
        // TODO: exportExcel???????????????IOException??????????????????????????????????????????
        assertThat(true);
    }

    /**
     * {@link Oa12010Controller#importExcel(Model,Oa12010Vo)}?????????
     *  ???????????????
     *    ??????
     *
     *  ???????????????
     *  ???Vo???????????????
     */
    @Test
    @Tag(TestSize.SMALL)
    void importExcel_test0() {

        // ??????????????????????????????
        Oa12010Controller oa12010Controller = createOa12010Controller(null);

        // ?????????
        ConcurrentModel model = new ConcurrentModel();
        Oa12010Vo Oa12010Vo = createOa12010Vo();
        subSystemCode = SubSystem.??????_??????.getCode();
        Oa12010Dto oa12010Dto = createOa12010Dto();
        MockHttpServletRequest mockHttpServletReques = new MockHttpServletRequest();
        oa12010Controller.session = mockHttpServletReques.getSession();
        oa12010Controller.session.setAttribute(oa12010Controller.SESSION_KEY_OA12010DTO,oa12010Dto);

        // ?????????
        String expectedViewName = "oa12010";
        subSystemCode = "";
        messageVoList = newArrayList();
        Oa12010Vo expectedVo = createOa12010Vo();

        // ??????
        String actualViewName = oa12010Controller.importExcel(model, Oa12010Vo);
        Oa12010Vo actualVo = (Oa12010Vo) model.getAttribute("form");

        // ????????????
        assertThat(actualViewName).isEqualTo(expectedViewName);
        assertThat(actualVo).usingRecursiveComparison().isEqualTo(expectedVo);
    }

    /**
     * {@link Oa12010Controller#importExcel(Model,Oa12010Vo)}?????????
     *  ???????????????
     *    ?????????RuntimeException?????????
     *
     *  ???????????????
     *  ????????????
     *  ???????????????????????????????????????
     */
    @Test
    @Tag(TestSize.SMALL)
    void importExcel_test1() {

        // ??????????????????????????????
        Oa12010Controller oa12010Controller = createOa12010Controller(-1);

        // ?????????
        ConcurrentModel model = new ConcurrentModel();
        Oa12010Vo Oa12010Vo = createOa12010Vo();
        subSystemCode = SubSystem.??????_??????.getCode();
        Oa12010Dto oa12010Dto = createOa12010Dto();
        MockHttpServletRequest mockHttpServletReques = new MockHttpServletRequest();
        oa12010Controller.session = mockHttpServletReques.getSession();
        oa12010Controller.session.setAttribute(oa12010Controller.SESSION_KEY_OA12010DTO,oa12010Dto);

        // ?????????
        String expectedViewName = "oa19999";
        String expectedMessageCode = "EOA10001";

        // ??????
        String actualViewName = oa12010Controller.importExcel(model, Oa12010Vo);
        Oa12010Vo actualVo = (Oa12010Vo) model.getAttribute("form");

        // ????????????
        assertThat(actualViewName).isEqualTo(expectedViewName);
        assertThat(actualVo.getMessageCode()).isEqualTo(expectedMessageCode);
    }

    /**
     * {@link Oa12010Controller#importExcel(Model,Oa12010Vo)}?????????
     *  ???????????????
     *    ?????????GunmaRuntimeException ?????????
     *
     *  ???????????????
     *  ????????????
     *  ???????????????????????????????????????
     */
    @Test
    @Tag(TestSize.SMALL)
    void importExcel_test2() {

        // ??????????????????????????????
        Oa12010Controller oa12010Controller = createOa12010Controller(-2);

        // ?????????
        ConcurrentModel model = new ConcurrentModel();
        Oa12010Vo Oa12010Vo = createOa12010Vo();
        subSystemCode = SubSystem.??????_??????.getCode();
        Oa12010Dto oa12010Dto = createOa12010Dto();
        MockHttpServletRequest mockHttpServletReques = new MockHttpServletRequest();
        oa12010Controller.session = mockHttpServletReques.getSession();
        oa12010Controller.session.setAttribute(oa12010Controller.SESSION_KEY_OA12010DTO,oa12010Dto);

        // ?????????
        String expectedViewName = "oa12010";

        // ??????
        String actualViewName = oa12010Controller.importExcel(model, Oa12010Vo);
        Oa12010Vo actualVo = (Oa12010Vo) model.getAttribute("form");

        // ????????????
        assertThat(actualViewName).isEqualTo(expectedViewName);
        assertThat(actualVo.getMessageCode()).isEqualTo(GunmaRuntimeExceptionMessageCode);
        assertThat(actualVo.getMessageArgs().get(0)).isEqualTo(GunmaRuntimeExceptionMessageArg1);
        assertThat(actualVo.getMessageArgs().get(1)).isEqualTo(GunmaRuntimeExceptionMessageArg2);
    }
}