<?php


namespace BaskelBundle\Entity;
use Doctrine\ORM\Mapping as ORM;

/**
 * Signaler
 *
 * @ORM\Table(name="likes")
 * @ORM\Entity(repositoryClass="BaskelBundle\Repository\LikesRepository")
 */
class Likes
{
    /**
     * @var int
     *
     * @ORM\Column(name="idLike", type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    private $idLike;

    /**
     * @var int
     *
     * @ORM\Column(name="idUser", type="integer")
     */
    private $idUser;

    /**
     * @var string
     *
     * @ORM\Column(name="Username", type="string", length=255)
     */
    private $username;

    /**
     * @var int
     *
     * @ORM\Column(name="idArticle", type="integer")
     */
    private $idArticle;

    /**
     * @return int
     */
    public function getIdLike()
    {
        return $this->idLike;
    }

    /**
     * @param int $idLike
     */
    public function setIdLike($idLike)
    {
        $this->idLike = $idLike;
    }




    /**
     * Set idUser
     *
     * @param integer $idUser
     *
     * @return Signaler
     */
    public function setIdUser($idUser)
    {
        $this->idUser = $idUser;

        return $this;
    }

    /**
     * Get idUser
     *
     * @return int
     */
    public function getIdUser()
    {
        return $this->idUser;
    }

    /**
     * Set username
     *
     * @param string $username
     *
     * @return String
     */
    public function setUsername($username)
    {
        $this->username = $username;

        return $this;
    }

    /**
     * Get username
     *
     * @return string
     */
    public function getUsername()
    {
        return $this->username;
    }

    /**
     * Set idArticle
     *
     * @param integer $idArticle
     *
     * @return String
     */
    public function setIdArticle($idArticle)
    {
        $this->idArticle = $idArticle;
        return $this;
    }

    /**
     * Get idArticle
     *
     * @return int
     */
    public function getIdArticle()
    {
        return $this->idArticle;
    }
}

